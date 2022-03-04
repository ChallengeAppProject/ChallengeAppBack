package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;

import java.util.List;

public interface AnswerService {
    List<ChallengeAnswer> getAllAnswer();
    public ChallengeAnswer saveAnswer(ChallengeAnswer challengeAnswer);
    public ChallengeAnswer get(Long id);

}
