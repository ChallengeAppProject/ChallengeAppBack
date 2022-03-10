package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
@CrossOrigin
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @GetMapping("/challenges")
    public List<Challenge> getAllChallenges(){
        return challengeService.getAllChallenges();
    }


    @GetMapping("/challenges/{id}")
    public ResponseEntity<Challenge> get(@PathVariable Long id) {

        try {
            Challenge challenge = challengeService.get(id);
            return new ResponseEntity<Challenge>(challenge, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Challenge>(HttpStatus.NOT_FOUND);

        }


    }
    @PostMapping("/challenges")
    public String addChallenge(@RequestBody Challenge challenge) {
        challengeService.saveChallenge(challenge);
        return "New Challenge created";
    }

    @DeleteMapping("/challenges/{id}")
    public String delete(@PathVariable Long id){
       challengeService.delete(id);
        return "Deleted challenge "+id;
    }

    @PutMapping("/challenges/{id}")
    public ResponseEntity<Challenge> update (@RequestBody Challenge challenge, @PathVariable Long id) {
        try {
            Challenge existingChallenge = challengeService.get(id);
            challengeService.save(challenge);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Challenge>(HttpStatus.NOT_FOUND);
        }

    }

 /*   @GetMapping("/challenges/{id}/questions")
    public List<Question> getAllQuestions(@PathVariable Long id) {
        return challengeService.getAllQuestionsByChallengeId();
    }*/

}
