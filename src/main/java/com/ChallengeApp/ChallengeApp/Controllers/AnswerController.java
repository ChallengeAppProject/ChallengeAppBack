package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
@CrossOrigin
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/answers")
    public List<ChallengeAnswer> getAllAnswer(){
        return answerService.getAllAnswer();
    }
    @PostMapping("/answers")
   public String addAnswer(@RequestBody ChallengeAnswer challengeAnswer) {
    answerService.saveAnswer(challengeAnswer);
    return "New answer created";
    }
    @GetMapping("/answers/{id}")
    public ResponseEntity<ChallengeAnswer> get(@PathVariable Long id) {

        try {
            ChallengeAnswer challengeAnswer = answerService.get(id);
            return new ResponseEntity<ChallengeAnswer>(challengeAnswer, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ChallengeAnswer>(HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping("/answers/{id}")
    public String delete(@PathVariable Long id){
        answerService.delete(id);
        return "Deleted answer "+id;
    }

    @PutMapping("/answers/{id}")
    public ResponseEntity<ChallengeAnswer> update (@RequestBody ChallengeAnswer challengeAnswer, @PathVariable Long id) {
        try {
            ChallengeAnswer existingChallengeAnswer = answerService.get(id);
            answerService.save(challengeAnswer);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ChallengeAnswer>(HttpStatus.NOT_FOUND);
        }
    }


}
