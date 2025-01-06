package com.smu.controller.Project;

import com.smu.service.Project.ProjectServiceImpl;
import com.smu.model.Project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    // Controller for adding a new project
    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.addProject(project));
    }

    // Controller for getting all projects
    @GetMapping("/all")
    public ResponseEntity<List<Project>> findAllProjects() {
        List<Project> projects = projectService.findAllProjects();
        return ResponseEntity.ok(projects);
    }

    // Get a project by id
    @GetMapping("/{id}")
    public ResponseEntity<Project> findProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findProjectById(id));
    }
    // Delete a project by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        //return project delete successfully message
        return ResponseEntity.ok("Project with id " + id + " deleted");
    }

}