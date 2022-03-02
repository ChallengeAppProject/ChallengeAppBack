package com.ChallengeApp.ChallengeApp.QuestionTest;

import com.ChallengeApp.ChallengeApp.Models.Question;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class QuestionTest {

    @Test
    void questionHasIDChallengeQuestionIMGAndChallengeIDProperty() {
        Question question = new Question(1L, "fotoDeTest","Como estás?");
        assertThat(question.getId(), equalTo(1L));
        assertThat(question.getImgUrl(), equalTo("fotoDeTest"));
        assertThat(question.getChallengeQuestion(),equalTo("Como estás?"));
        

    }
}
