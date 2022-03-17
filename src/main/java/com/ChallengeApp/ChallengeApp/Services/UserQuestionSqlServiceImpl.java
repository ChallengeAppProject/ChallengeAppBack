package com.ChallengeApp.ChallengeApp.Services;


import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.User;
import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import com.ChallengeApp.ChallengeApp.Repositories.UserQuestionRepository;
import com.ChallengeApp.ChallengeApp.dtos.QuestionListResponseDTO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserQuestionSqlServiceImpl implements UserQuestionService {

    private UserQuestionRepository userQuestionRepository;

    public UserQuestionSqlServiceImpl(UserQuestionRepository userQuestionRepository) {
        this.userQuestionRepository = userQuestionRepository;
    }

    @Override
    public QuestionListResponseDTO getAllfindAllByUserAndQuestion_Challenge(User user, Challenge challenge){
        List<UserQuestion> userQuestions= userQuestionRepository.findAllByUserAndQuestion_Challenge(user, challenge);
        QuestionListResponseDTO questionListResponseDTO = new QuestionListResponseDTO();
        questionListResponseDTO.mapFromQuestionsList(userQuestions);
        return questionListResponseDTO;
    }
}
