package com.ChallengeApp.ChallengeApp.Services;


import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.User;
import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import com.ChallengeApp.ChallengeApp.Repositories.*;
import com.ChallengeApp.ChallengeApp.dtos.QuestionListResponseDTO;

import com.ChallengeApp.ChallengeApp.dtos.UserQuestionRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.UserQuestionResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQuestionServiceImpl implements UserQuestionService {

    private UserQuestionRepository userQuestionRepository;
    private AnswerRepository answerRepository;
    private UserRepository userRepository;
    private QuestionRepository questionRepository;
    private ChallengeAppRepository challengeAppRepository;

    public UserQuestionServiceImpl(UserQuestionRepository userQuestionRepository, AnswerRepository answerRepository,
                                   UserRepository userRepository, QuestionRepository questionRepository, ChallengeAppRepository challengeAppRepository) {
        this.userQuestionRepository = userQuestionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.challengeAppRepository = challengeAppRepository;
    }

    @Override
    public QuestionListResponseDTO findAllByUserAndQuestion_Challenge(User user, Challenge challenge){
        List<UserQuestion> userQuestions= userQuestionRepository.findAllByUserAndQuestion_Challenge(user, challenge);
        QuestionListResponseDTO questionListResponseDTO = new QuestionListResponseDTO();
        questionListResponseDTO.mapFromQuestionsList(userQuestions);
        return questionListResponseDTO;
    }

    @Override
    public UserQuestionResponseDTO save(UserQuestionRequestDTO userQuestionRequestDTO) {
        var userQuestion = new UserQuestion();
        userQuestion.setUser(userRepository.findById(userQuestionRequestDTO.getUserId()).get());
        userQuestion.setQuestion(questionRepository.findById(userQuestionRequestDTO.getQuestionId()).get());
        userQuestion.setChallengeAnswer(answerRepository.findById(userQuestionRequestDTO.getChallengeAnswerId()).get());

        //if (userQuestionRepository.existsById())

        userQuestionRepository.save(userQuestion);
        var userQuestionResponse = new UserQuestionResponseDTO().mapFromUserQuestion(userQuestion);

        return userQuestionResponse;
    }
}
