package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.User;
import com.ChallengeApp.ChallengeApp.dtos.QuestionListResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.UserQuestionRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.UserQuestionResponseDTO;


public interface UserQuestionService {

    QuestionListResponseDTO getAllfindAllByUserAndQuestion_Challenge(User user, Challenge challenge);
    UserQuestionResponseDTO save(UserQuestionRequestDTO userQuestionRequestDTO);





}
