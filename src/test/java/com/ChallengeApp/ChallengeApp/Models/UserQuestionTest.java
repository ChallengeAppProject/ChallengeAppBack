package com.ChallengeApp.ChallengeApp.Models;

import com.ChallengeApp.ChallengeApp.Services.UserQuestionService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class UserQuestionTest {


    @Test
    void ifAnswerisRight() {
        Challenge challenge = new Challenge(1L,"Matematicas");
        Question question1 = new Question();
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true,"Es cierto",question1 );
        question1.setChallenge(challenge);
        User user1 = new User(2L, "Pepe");


        UserQuestion userQuestion = new UserQuestion(2L,user1, question1, challengeAnswer1);
       var sut = userQuestion.isUserRight();

       assertTrue(sut);
    }
}