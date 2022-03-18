package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.dtos.AnswerRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;

import java.util.List;

public interface AnswerService {
    List<AnswerResponseDTO> getAllAnswer();
    public AnswerResponseDTO saveAnswer(AnswerRequestDTO challengeAnswer);
    public AnswerResponseDTO getAnswerById(Long id);
    public void delete (Long id);
    public AnswerResponseDTO createAnswer (AnswerRequestDTO answerRequestDTO);

    List<AnswerResponseDTO> getAllByQuestion(Question question);
}
