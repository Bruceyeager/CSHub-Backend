package com.smu.repository.Project;

import com.smu.model.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository  extends  JpaRepository<Project, Long> {

}
