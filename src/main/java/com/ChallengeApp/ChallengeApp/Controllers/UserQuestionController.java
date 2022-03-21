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
import com.ChallengeApp.ChallengeApp.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping
public class UserQuestionController {


    private UserQuestionService userQuestionService;
    private ChallengeService challengeService;
    @Autowired
    private UserRepository userRepository;

    public UserQuestionController(UserQuestionService userQuestionService, ChallengeService challengeService) {
        this.userQuestionService = userQuestionService;
        this.challengeService = challengeService;
    }

    private User getAuthUser() {

        return userRepository.getById(1L);
    }


    @GetMapping("/userQuestion/challenge/{id}")
    public QuestionListResponseDTO getAllfindAllByUserAndQuestion_Challenge(@PathVariable Long id) {
        User user = getAuthUser();
        Challenge challenge = challengeService.getById(id);
        return userQuestionService.getAllfindAllByUserAndQuestion_Challenge(user, challenge);
    }

    @PostMapping("/userQuestion/challenge/{id}")
    public ResponseEntity<UserQuestionResponseDTO> addUserAnswer(@RequestBody UserQuestionRequestDTO userQuestionRequestDTO,
                                                                 @PathVariable Long id) {
        try {
            userQuestionRequestDTO.setChallengeId(id);
            userQuestionRequestDTO.setUserId(1L);
            UserQuestionResponseDTO userQuestionResponseDTO = userQuestionService.save(userQuestionRequestDTO);
            return new ResponseEntity<UserQuestionResponseDTO>(userQuestionResponseDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<UserQuestionResponseDTO>(HttpStatus.NOT_FOUND);
        }
    }
}




