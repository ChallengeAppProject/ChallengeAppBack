package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping

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
}
