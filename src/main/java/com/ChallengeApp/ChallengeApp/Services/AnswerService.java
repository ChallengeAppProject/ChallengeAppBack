package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;

import java.util.List;

public interface AnswerService {
    List<ChallengeAnswer> getAllAnswer();
    public ChallengeAnswer saveAnswer(ChallengeAnswer challengeAnswer);
    public ChallengeAnswer get(Long id);
    public void delete (Long id);
    public ChallengeAnswer save (ChallengeAnswer challengeAnswer);


    List<AnswerResponseDTO> getAllByQuestion(Question question);
}
