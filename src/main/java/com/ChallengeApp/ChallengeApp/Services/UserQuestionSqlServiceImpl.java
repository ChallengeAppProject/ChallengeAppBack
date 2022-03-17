package com.ChallengeApp.ChallengeApp.Services;


import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.User;
import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import com.ChallengeApp.ChallengeApp.Repositories.ChallengeAppRepository;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.Repositories.UserQuestionRepository;
import com.ChallengeApp.ChallengeApp.dtos.QuestionListResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.UserQuestionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

        //List<UserQuestionResponseDTO> userQuestionDTO = new ArrayList<UserQuestionResponseDTO>();

        /*for(UserQuestion userQuestion : userQuestions){
            UserQuestionResponseDTO userQuestionResponseDTO = new UserQuestionResponseDTO();
            userQuestionResponseDTO.mapFromUserQuestion(userQuestion);
            *//*userQuestionResponseDTO.setUserId(userQuestion.getId());
            userQuestionResponseDTO.setQuestionId(userQuestion.getQuestion().getId());
            userQuestionResponseDTO.setChallengeAnswerId(userQuestion.getChallengeAnswer().getId());
            userQuestionResponseDTO.setChallengeId(userQuestion.getChallengeAnswer().getQuestion().getChallenge().getId());*//*
            userQuestionDTO.add(userQuestionResponseDTO);
        }*/
        return questionListResponseDTO;
    }
}
