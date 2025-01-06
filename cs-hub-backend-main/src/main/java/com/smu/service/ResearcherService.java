package com.smu.service;

import com.smu.model.Researcher;
import com.smu.repository.ResearcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResearcherService {

    @Autowired
    private ResearcherRepository researcherRepository;

    // Add a new researcher
    public Researcher addResearcher(Researcher researcher) {
        return researcherRepository.save(researcher);
    }

    // Find all researchers
    public List<Researcher> findAllResearchers() {
        return researcherRepository.findAll();
    }

    // Find a researcher by ID
    public Optional<Researcher> findOneById(Long id) {
        return researcherRepository.findById(id);
    }

    // Delete a researcher by ID
    public void deleteById(Long id) {
        if (researcherRepository.existsById(id)) {
            researcherRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Researcher with ID " + id + " does not exist.");
        }
    }
}
