package com.ChallengeApp.ChallengeApp.ModelsTest;

import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    @Test

     void answerHasIDQuestionIDAnswerBooleanIDProperties(){
        Question questionCiencias = new Question();
        ChallengeAnswer challengeAnswer = new ChallengeAnswer(1L, true, "text answer", questionCiencias);

        assertThat(challengeAnswer.getId(), equalTo(1L));
        assertTrue(challengeAnswer.isCorrect());
        assertThat(challengeAnswer.getTextAnswer(), equalTo("text answer"));
        assertThat(challengeAnswer.getQuestion(), equalTo(questionCiencias));
    }
}
