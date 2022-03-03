package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping
//@CrossOrigin
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @GetMapping("/challenges/{id}")
    public ResponseEntity<Challenge> get(@PathVariable Long id) {

        try {
            Challenge challenge = challengeService.get(id);
            return new ResponseEntity<Challenge>(challenge, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Challenge>(HttpStatus.NOT_FOUND);

        }


    }

}
