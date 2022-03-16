package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.dtos.QuestionRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;

import java.util.List;

public interface QuestionService {

    public QuestionResponseDTO get(Long id);

    List<QuestionResponseDTO> getAllQuestion();

    public String delete (Long id);


     List<QuestionResponseDTO> getAllByChallenge(Challenge challenge);

    QuestionResponseDTO saveQuestion(QuestionRequestDTO questionRequestDTO, Long id);

    public QuestionResponseDTO createQuestion(QuestionRequestDTO questionRequestDTO);

}
