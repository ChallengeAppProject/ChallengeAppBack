package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions")
    public List<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }


    @PostMapping("/questions")
    public String addQuestion(@RequestBody Question question) {
        questionService.saveQuestion(question);
        return "New question created";
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<Question> get(@PathVariable Long id) {

        try {
            Question question = questionService.get(id);
            return new ResponseEntity<Question>(question, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);

        }

    }
}
