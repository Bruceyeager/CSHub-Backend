package com.smu.controller;

import com.smu.model.Researcher;
import com.smu.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/researchers")
public class ResearcherController {

    @Autowired
    private ResearcherService researcherService;

    // POST: Add a new researcher
    @PostMapping("/add")
    public ResponseEntity<Researcher> addResearcher(@RequestBody Researcher researcher) {
        Researcher newResearcher = researcherService.addResearcher(researcher);
        return ResponseEntity.ok(newResearcher);
    }

    // GET: Find all researchers
    @GetMapping("/findAll")
    public ResponseEntity<List<Researcher>> findAllResearchers() {
        List<Researcher> researchers = researcherService.findAllResearchers();
        return ResponseEntity.ok(researchers);
    }

    // GET: Find a researcher by ID
    @GetMapping("/findOneById/{id}")
    public ResponseEntity<Researcher> findOneById(@PathVariable Long id) {
        Optional<Researcher> researcher = researcherService.findOneById(id);
        return researcher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Delete a researcher by ID
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            researcherService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
