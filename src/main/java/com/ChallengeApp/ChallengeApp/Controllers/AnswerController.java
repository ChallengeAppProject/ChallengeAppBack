package com.ChallengeApp.ChallengeApp.Controllers;
import com.ChallengeApp.ChallengeApp.Services.AnswerService;
import com.ChallengeApp.ChallengeApp.dtos.AnswerRequestDTO;
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
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/answers")
    public List<AnswerResponseDTO> getAllAnswer(){
        return answerService.getAllAnswer();
    }

    @PostMapping("/questions/{id}/answer")
   public ResponseEntity<AnswerResponseDTO> addAnswer(@PathVariable Long id, @RequestBody AnswerRequestDTO answerRequestDTO) {
        try{
            answerRequestDTO.setQuestionId(id);
            AnswerResponseDTO answerResponseDTO = answerService.createAnswer(answerRequestDTO);
            return new ResponseEntity<AnswerResponseDTO>(answerResponseDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<AnswerResponseDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/answers/{id}")
    public ResponseEntity<AnswerResponseDTO> get(@PathVariable Long id) {

        try {
            AnswerResponseDTO answer = answerService.getAnswerById(id);
            return new ResponseEntity<AnswerResponseDTO>(answer, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<AnswerResponseDTO>(HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping("/answers/{id}")
    public String delete(@PathVariable Long id){
        answerService.delete(id);
        return "Deleted answer "+id;
    }

    @PutMapping("/answers/{id}")
    public ResponseEntity<AnswerResponseDTO> update (@RequestBody AnswerRequestDTO answerRequestDTO, @PathVariable Long id) {
        try {
            AnswerResponseDTO answer = answerService.getAnswerById(id);
            answerService.saveAnswer(answerRequestDTO);
            return new ResponseEntity<AnswerResponseDTO>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<AnswerResponseDTO>(HttpStatus.NOT_FOUND);
        }
    }


}
