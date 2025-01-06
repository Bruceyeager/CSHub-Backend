package com.smu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smu.model.Researcher;

public interface ResearcherRepository extends JpaRepository<Researcher, Long> {

}
