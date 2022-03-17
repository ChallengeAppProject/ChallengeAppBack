package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Models.User;
import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import com.ChallengeApp.ChallengeApp.Repositories.ChallengeAppRepository;
import com.ChallengeApp.ChallengeApp.Repositories.UserQuestionRepository;
import com.ChallengeApp.ChallengeApp.Repositories.UserRepository;
import com.ChallengeApp.ChallengeApp.Services.ChallengeService;
import com.ChallengeApp.ChallengeApp.Services.QuestionService;
import com.ChallengeApp.ChallengeApp.Services.UserQuestionService;
import com.ChallengeApp.ChallengeApp.Services.UserQuestionSqlServiceImpl;
import com.ChallengeApp.ChallengeApp.dtos.UserQuestionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping
public class UserQuestionController {


    private UserQuestionService userQuestionService;
    private ChallengeService challengeService;
    @Autowired
    private UserRepository userRepository;

    public UserQuestionController(UserQuestionService userQuestionService, ChallengeService challengeService){
        this.userQuestionService = userQuestionService;
        this.challengeService = challengeService;
    }

    private User getAuthUser (){

        return userRepository.getById(1L);
    }


    @GetMapping("/userQuestion/challenge/{id}")
    public List<UserQuestionResponseDTO> getAllfindAllByUserAndQuestion_Challenge( @PathVariable Long id) {
        User user= getAuthUser();
        Challenge challenge = challengeService.getById(id);
        return userQuestionService.getAllfindAllByUserAndQuestion_Challenge(user, challenge );
    }
}

