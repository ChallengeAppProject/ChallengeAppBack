package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Services.ChallengeService;
import com.ChallengeApp.ChallengeApp.Services.QuestionService;
import com.ChallengeApp.ChallengeApp.dtos.ChallengeRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.ChallengeResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
@CrossOrigin
public class ChallengeController {
    private QuestionService questionService;
    private ChallengeService challengeService;

    public ChallengeController(QuestionService questionService, ChallengeService challengeService){
        this.questionService = questionService;
        this.challengeService = challengeService;
    }

    @GetMapping("/challenges")
    public List<ChallengeResponseDTO> getAllChallenges(){
        return challengeService.getAllChallenges();
    }


    @GetMapping("/challenges/{id}")
    public ResponseEntity<ChallengeResponseDTO> get(@PathVariable Long id) {

        try {
            ChallengeResponseDTO challengeResponseDTO = challengeService.get(id);
            return new ResponseEntity<ChallengeResponseDTO> (challengeResponseDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ChallengeResponseDTO>(HttpStatus.NOT_FOUND);

        }

    }
    @PostMapping("/challenges")
    public String addChallenge(@RequestBody ChallengeRequestDTO challengeRequestDTO) {
        challengeService.createChallenge(challengeRequestDTO);
        return "New Challenge created";
    }

    @DeleteMapping("/challenges/{id}")
    public String delete(@PathVariable Long id){
        ChallengeResponseDTO challengeResponseDTO = new ChallengeResponseDTO();
        challengeResponseDTO.setId(id);
       challengeService.delete(challengeResponseDTO.getId());
        return "Deleted challenge "+id;
    }

    @PutMapping("/challenges/{id}")
    public ResponseEntity<ChallengeResponseDTO> update (@RequestBody ChallengeRequestDTO challengeRequestDTO, @PathVariable Long id) {
        try {
            ChallengeResponseDTO existingChallenge = challengeService.get(id);
            challengeService.saveChallenge(challengeRequestDTO,existingChallenge.id);
            return new ResponseEntity<ChallengeResponseDTO>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ChallengeResponseDTO>(HttpStatus.NOT_FOUND);
        }

    }

   @GetMapping("/challenges/{id}/questions")
    public List<QuestionResponseDTO> getAllQuestions(@PathVariable Long id, Challenge challenge) {
        return questionService.getAllByChallenge(challenge);
    }

}
