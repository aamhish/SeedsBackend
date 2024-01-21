package com.example.demo.controller;

import com.example.demo.model.Challenge;
import com.example.demo.services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {
        // Add logging to print the values before saving
        System.out.println("Received Challenge Data:");
        System.out.println("Title: " + challenge.getTitle());
        System.out.println("Description: " + challenge.getDescription());
        System.out.println("Duration: " + challenge.getDuration());
        System.out.println("Friend: " + challenge.getFriend());

        Challenge savedChallenge = challengeService.saveChallenge(challenge);
        return new ResponseEntity<>(savedChallenge, HttpStatus.CREATED);
    }
    @GetMapping("/{postNum}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable Long postNum) {
        Optional<Challenge> challenge = challengeService.getChallengeById(postNum);
        return challenge.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        if (!challenges.isEmpty()) {
            return new ResponseEntity<>(challenges, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/refresh")
    public ResponseEntity<List<Challenge>> refreshChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        if (!challenges.isEmpty()) {
            return new ResponseEntity<>(challenges, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}