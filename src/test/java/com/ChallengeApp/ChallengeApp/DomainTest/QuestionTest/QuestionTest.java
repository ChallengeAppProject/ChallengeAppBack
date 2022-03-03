package com.ChallengeApp.ChallengeApp.DomainTest.QuestionTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class QuestionTest {

    @Test
    void questionHasIDChallengeQuestionIMGAndChallengeIDProperty() {
        Challenge challengeCiencias = new Challenge(1L, "Prueba");
        Question question = new Question(1L, "fotoDeTest","Como estás?", challengeCiencias);

        assertThat(question.getId(), equalTo(1L));
        assertThat(question.getImgUrl(), equalTo("fotoDeTest"));
        assertThat(question.getChallengeQuestion(),equalTo("Como estás?"));
        assertThat(question.getChallenge(),equalTo(challengeCiencias));
    }

}
