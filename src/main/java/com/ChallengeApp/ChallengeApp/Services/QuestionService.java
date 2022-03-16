package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;

import java.util.List;

public interface QuestionService {

    public QuestionResponseDTO get(Long id);

    List<QuestionResponseDTO> getAllQuestion();

    public Question saveQuestion(Question question);


    public void delete (Long id);

    public QuestionResponseDTO save (QuestionResponseDTO questionResponseDTO);

     List<QuestionResponseDTO> getAllByChallenge(Challenge challenge);

}
