package com.ChallengeApp.ChallengeApp.answerTest;

import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    @Test

     void answerHasIDQuestionIDAnswerBooleanIDProperties(){
        ChallengeAnswer challengeAnswer = new ChallengeAnswer(1L, true, "text answer", 1L);
        assertThat(challengeAnswer.getId(), equalTo(1L));
        assertTrue(challengeAnswer.isCorrect());
        assertThat(challengeAnswer.getTextAnswer(), equalTo("text answer"));
        assertThat(challengeAnswer.getQuestionId(), equalTo(1L));
    }
}
