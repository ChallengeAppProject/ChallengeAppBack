package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Services.AnswerService;
import com.ChallengeApp.ChallengeApp.Services.QuestionService;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;
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
    private AnswerService answerService;

   private QuestionService questionService;

   public QuestionController(QuestionService questionService, AnswerService answerService){
       this.questionService = questionService;
       this.answerService = answerService;
   }

    @GetMapping("/questions")
    public List<QuestionResponseDTO> getAllQuestion() {
        return questionService.getAllQuestion();
    }


    @PostMapping("/questions")
    public String addQuestion(@RequestBody QuestionResponseDTO questionResponseDTO) {
        questionService.saveQuestion(questionRequestDTO);
        return "New question created";
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<QuestionResponseDTO> get(@PathVariable Long id) {

        try {
            QuestionResponseDTO questionResponseDTO = questionService.get(id);
            return new ResponseEntity<QuestionResponseDTO>(questionResponseDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<QuestionResponseDTO>(HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping("/questions/{id}")
    public String delete(@PathVariable Long id){
        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        questionResponseDTO.setId(id);
        questionService.delete(questionResponseDTO.getId());
        return "Deleted question "+id;
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> update (@RequestBody QuestionResponseDTO questionResponseDTO, @PathVariable Long id) {
        try {
            QuestionResponseDTO existingQuestion = questionService.get(id);
            questionService.save(questionResponseDTO);
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
