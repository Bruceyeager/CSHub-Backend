package com.smu.service;

import com.smu.model.Challenge;
import com.smu.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService{

    @Autowired
    private ChallengeRepository challengeRepository;

    public Challenge addChallenge(Challenge challenge) {
        return challengeRepository.save(challenge); //save challenge
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll(); //get all challenges
    }

    public Challenge getChallengeById(Long id) {
        return challengeRepository.findById(id).orElse(null); //get challenge using ID
    }

    public Challenge deleteChallengeById(Long id) {
        Challenge deletedChallenge = challengeRepository.findById(id).orElse(null);
        if (deletedChallenge != null) {
            challengeRepository.deleteById(id);
        }
        return deletedChallenge;
    }
}
