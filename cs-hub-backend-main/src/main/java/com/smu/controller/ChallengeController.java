package com.smu.controller;

import com.smu.model.Challenge;
import com.smu.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/add")
    public ResponseEntity<Challenge> addChallenge(@RequestBody Challenge challenge) {
        Challenge createdChallenge = challengeService.addChallenge(challenge);
        return ResponseEntity.ok().body(createdChallenge);
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> allChallenges = challengeService.getAllChallenges();
        return ResponseEntity.ok().body(allChallenges);
    }

    @GetMapping("/{id}")
    public Challenge getChallenge(@PathVariable Long id) {
        return challengeService.getChallengeById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Challenge> deleteChallengeById(@PathVariable Long id) {
        Challenge deletedChallenge = challengeService.deleteChallengeById(id);
        return ResponseEntity.ok().body(deletedChallenge);
    }
}
