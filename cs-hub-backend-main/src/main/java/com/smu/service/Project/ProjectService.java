package com.smu.service.Project;

import com.smu.model.Project.Project;

import java.util.List;

public interface ProjectService {

    // Add a new project
    Project addProject(Project project);

    // Get all projects
    List<Project> findAllProjects();

    // Get a project by id
    Project findProjectById(Long id);

    // Delete a project by id
    void deleteProjectById(Long id);
}
