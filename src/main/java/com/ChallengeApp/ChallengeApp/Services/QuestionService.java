package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.dtos.QuestionRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;

import java.util.List;

public interface QuestionService {

    public QuestionResponseDTO get(Long id);

    public List<QuestionResponseDTO> getAllQuestion();

    public String delete (Long id);


    public List<QuestionResponseDTO> getAllByChallenge(Challenge challenge);

    public QuestionResponseDTO saveQuestion(QuestionRequestDTO questionRequestDTO, Long id);

    public QuestionResponseDTO createQuestion(QuestionRequestDTO questionRequestDTO);

}
