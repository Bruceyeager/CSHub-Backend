package com.smu.service.Project;

import com.smu.model.Project.Project;
import com.smu.repository.Project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService{


    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Add a new project
    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    // Get all projects
    @Override
    public List<Project> findAllProjects() {
        try {
            return projectRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("No projects found");
        }
    }

    // Get a project by id
    @Override
    public Project findProjectById(Long id) {
        try {
            return projectRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("Project not found with id: " + id);
        }
    }

    // Delete a project by id
    @Override
    public void deleteProjectById(Long id) {
        try {
            projectRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Project not found with id: " + id);
        }
    }
}
