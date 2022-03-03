package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;

import java.util.List;

public interface AnswerService {
    List<ChallengeAnswer> getAllAnswer();
    public ChallengeAnswer saveAnswer(ChallengeAnswer challengeAnswer);
}
