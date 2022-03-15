package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Models.User;
import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import com.ChallengeApp.ChallengeApp.Repositories.ChallengeAppRepository;
import com.ChallengeApp.ChallengeApp.Repositories.UserQuestionRepository;
import com.ChallengeApp.ChallengeApp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping
public class UserQuestionController {

    @Autowired
    UserQuestionRepository userQuestionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChallengeAppRepository challengeAppRepository;

    @GetMapping("/userQuestion")
    public List<UserQuestion> getAll() {
         var user = userRepository.findById(1L).get();
         var chall = challengeAppRepository.findById(1L).get();
        return userQuestionRepository.findAllByUserAndQuestion_Challenge(user, chall);
    }
}
