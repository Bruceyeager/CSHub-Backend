# CS-Hub

## Overview
This is a backend service project based on Spring Boot and MySQL, containerized using Docker to manage the application and database.

## Requirements
- **JDK 17**
- **Maven 3.6+**
- **Docker**
- **Docker Compose**

## Steps to Run Locally

### 1. Check if the Project Builds Successfully

First, use Maven to clean and build the project:

```bash
mvn clean install
```

If the build succeeds, you should see a BUILD SUCCESS message.

### 2. Start Docker Containers
Run the following command to start the application and database containers:

```bash
docker-compose up
```

This will spin up two containers:

- app: Contains the Spring Boot application.
- db: Contains the MySQL database (MySQL 5.7).

### 3. Remove the app Docker Image
After starting the containers, manually delete the app image either using the command line or the Docker Desktop UI. This ensures that the application can be run directly via IntelliJ or from the command line.

Command to remove the app image:

```bash
docker rmi app
```

### 4. Run the Application via IntelliJ or Command Line
Once the app image is removed, you can run the application either through IntelliJ IDEA or using the command line:

- Run via IntelliJ IDEA:

Open the project in IntelliJ.
Navigate to the CS-Hub application class (usually located in src/main/java).
Right-click on the class and select Run 'CS-HubApplication'.

- Run via command line:

```bash
mvn spring-boot:run
```

### 5. Test the Application
Once the application is running, you can verify that it is working by visiting the following URL in your browser:

```bash
http://localhost:8080/health
```
This will check if the service is up and running.

## Project Structure

This project is a Spring Boot application that follows a modular architecture, organized into different packages for maintainability and scalability. Below is a detailed breakdown of the project structure.


```bash
.
├── .mvn/wrapper
│   ├── maven-wrapper.jar
│   ├── maven-wrapper.properties
├── imgs
│   ├── springboot-boilerplate.jpg
│   ├── swagger-1.jpg
│   ├── swagger-2.jpg
├── src/main
│   ├── java/com/smu
│   │   ├── common
│   │   │   └── # Common utilities, constants, and helpers.
│   │   ├── configuration
│   │   │   └── # Configuration classes for Spring beans, security, etc.
│   │   ├── controller
│   │   │   └── # REST API controllers that handle incoming HTTP requests.
│   │   ├── exceptions
│   │   │   └── # Custom exception handling for the application.
│   │   ├── model
│   │   │   └── # Entity classes and data models representing the application’s domain.
│   │   ├── repository
│   │   │   └── # Data access layer, containing interfaces for database operations.
│   │   ├── security
│   │   │   └── # Security configuration, e.g., authentication, authorization.
│   │   ├── service
│   │   │   └── # Service layer that handles business logic.
│   │   ├── utils
│   │       └── # Utility classes for common operations like date parsing, validation, etc.
│   ├── resources
│   │   ├── messages
│   │   │   └── # Resource bundles for localized messages.
│   │   ├── exception
│   │   │   └── # Templates for exception messages.
│   │   ├── general
│   │   │   └── # General resource files, such as banners.
│   │   ├── validation
│   │       └── # Validation messages and configuration.
│   ├── application.yml
│   ├── banner.txt
├── .DS_Store
├── .dockerignore
├── .gitignore
├── Dockerfile
├── LICENSE
├── README.md
├── docker-compose.yml
├── local-docker-compose.yml
├── pom.xml
```


## Configuration Files
The project’s configuration file is application.yml, which sets the server port to 8080.
Database details are configured in the Docker Compose file local-docker-compose.yml.

## Notes
If you encounter a port conflict, ensure the correct port is set in the application.yml file (e.g., 8080).
Make sure Docker is properly installed and running locally.
Let me know if you need any other changes!

# Build POST/GET API

Here’s a generalized step-by-step guide to develop POST and GET APIs, with the detailed explanation for each step:

## 1. **Create an Entity Class**
- **Purpose**: Define the data model that represents a domain object in the database.
- **How**: In the `model` package, create a Java class annotated with `@Entity`. Use `@Id` to define the primary key and `@GeneratedValue` to specify the generation strategy.

  **Example**:
  ```java
  @Entity
  public class EntityName {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      
      private String name;
      private String otherField;

      // Constructors, getters, and setters
  }
  ```

## 2. **Create the Repository Interface**
- **Purpose**: Define the data access layer to handle interactions with the database.
- **How**: In the `repository` package, create an interface extending `JpaRepository<EntityName, Long>`. This provides built-in methods for CRUD operations.

  **Example**:
  ```java
  @Repository
  public interface EntityNameRepository extends JpaRepository<EntityName, Long> {
      // You can define custom query methods here
  }
  ```

## 3. **Create the Service Class**
- **Purpose**: Implement business logic to interact between the controller and repository layers.
- **How**: In the `service` package, create a class annotated with `@Service`. Use `@Autowired` to inject the repository and define methods to handle the business logic like adding and retrieving data.

  **Example**:
  ```java
  @Service
  public class EntityNameService {

      @Autowired
      private EntityNameRepository entityNameRepository;

      public EntityName addEntity(EntityName entity) {
          return entityNameRepository.save(entity);
      }

      public List<EntityName> getAllEntities() {
          return entityNameRepository.findAll();
      }
  }
  ```


## **4. Create the Controller Class**
**Purpose**: Handle HTTP requests and map them to service methods while maintaining RESTful standards.

### **For GET Requests**
**Purpose**: Retrieve data or resources without modifying server-side data.

**How**: Use `@GetMapping` for handling GET requests. You can define methods to retrieve all resources, or a specific resource by ID or other criteria. Extract path variables using `@PathVariable` and query parameters using `@RequestParam`.

#### **Example**:

```java
@RestController
@RequestMapping("/entities")
public class EntityNameController {

    @Autowired
    private EntityNameService entityNameService;

    // GET: Retrieve all entities
    @GetMapping
    public ResponseEntity<List<EntityName>> getAllEntities() {
        List<EntityName> entities = entityNameService.getAllEntities();
        return ResponseEntity.ok(entities);
    }

    // GET: Retrieve entity by ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityName> getEntityById(@PathVariable Long id) {
        EntityName entity = entityNameService.getEntityById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    // GET: Retrieve entities with pagination and sorting
    @GetMapping("/search")
    public ResponseEntity<List<EntityName>> searchEntities(
        @RequestParam int page, 
        @RequestParam int size, 
        @RequestParam String sort) {
        List<EntityName> entities = entityNameService.findEntitiesWithPagination(page, size, sort);
        return ResponseEntity.ok(entities);
    }
}
```

#### **Key Points**:
- **Use `@GetMapping`**: Define endpoints for retrieving resources.
- **Parameter Handling**: Extract path variables using `@PathVariable` and query parameters using `@RequestParam`.
- **Return Type**: Return the response using `ResponseEntity` to include appropriate status codes like 200 OK or 404 Not Found.

---

### **For POST Requests**
**Purpose**: Create new resources or submit data that modifies server-side content.

**How**: Use `@PostMapping` for handling POST requests. Accept data in the request body using `@RequestBody`, and pass it to the service layer for processing.

#### **Example**:

```java
@RestController
@RequestMapping("/entities")
public class EntityNameController {

    @Autowired
    private EntityNameService entityNameService;

    // POST: Create a new entity
    @PostMapping
    public ResponseEntity<EntityName> addEntity(@RequestBody EntityName entity) {
        EntityName createdEntity = entityNameService.addEntity(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEntity);
    }
}
```

#### **Key Points**:
- **Use `@PostMapping`**: Define endpoints for creating new resources.
- **Parameter Handling**: Use `@RequestBody` to accept JSON data in the request body and map it to a Java object.
- **Return Type**: Use `ResponseEntity` to control the HTTP status code (201 Created) and include the newly created resource in the response body.


## 5. **Test the APIs**
- **Purpose**: Verify that both POST and GET APIs work as expected.
- **How**: After running the Spring Boot project, use Postman or CURL to test the endpoints:
  - **POST Request**: Send a POST request with JSON data to `/entities`.
  - **GET Request**: Send a GET request to `/entities` to retrieve all entities.

  **POST Request Example**:
  ```json
  {
      "name": "Sample Entity",
      "otherField": "Sample Data"
  }
  ```

  **GET Request Example**:
  ```bash
  curl -X GET http://localhost:8080/entities
  ```

By following these steps, you can create a fully functional POST API or a GET API simply. You can also implement more complicated business logic by defining custom query methods/sevice functions/controller.
