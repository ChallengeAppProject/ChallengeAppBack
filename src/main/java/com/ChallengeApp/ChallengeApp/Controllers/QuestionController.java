package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Services.AnswerService;
import com.ChallengeApp.ChallengeApp.Services.ChallengeService;
import com.ChallengeApp.ChallengeApp.Services.QuestionService;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;
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
    private ChallengeService challengeService;
    private AnswerService answerService;

   private QuestionService questionService;

   public QuestionController(QuestionService questionService, AnswerService answerService){
       this.questionService = questionService;
       this.answerService = answerService;
   }

    @GetMapping("/questions")
    public List<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }


    @PostMapping("/challenges/{id}/question")
    public String addQuestion(@RequestBody QuestionRequestDTO questionRequestDTO, @PathVariable Long id) {
       try {
           questionRequestDTO.setChallengeId(id);
           QuestionResponseDTO questionResponseDTO = questionService.createQuestion(questionRequestDTO);
           return "New question created";
       } catch (NoSuchElementException e) {
           return "Error creating question";
       }

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

    @DeleteMapping("/questions/{id}")
    public String delete(@PathVariable Long id){
        questionService.delete(id);
        return "Deleted question "+id;
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> update (@RequestBody Question question, @PathVariable Long id) {
        try {
            Question existingQuestion = questionService.get(id);
            questionService.save(question);
            return new ResponseEntity<Question>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/questions/{id}/answers")
    public List<AnswerResponseDTO> getAllAnswers(@PathVariable Long id, Question question) {
        return answerService.getAllByQuestion(question);
    }

}
