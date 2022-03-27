package com.ChallengeApp.ChallengeApp.ModelsTest;

import com.ChallengeApp.ChallengeApp.Models.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserQuestionTest {


    @Test
    void ifAnswerisRight() {
        Challenge challenge = new Challenge(1L, "Matematicas");
        Question question1 = new Question();
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true, "Es cierto", question1);
        question1.setChallenge(challenge);
        User user1 = new User(2L, "Pepe");


        UserQuestion userQuestion = new UserQuestion(2L, user1, question1, challengeAnswer1);
        var sut = userQuestion.isUserRight();

        assertTrue(sut);
    }

    @Test
    void ifAnswerisWrong() {
        Challenge challenge = new Challenge(1L, "Matematicas");
        Question question1 = new Question();
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true, "Es cierto", question1);
        ChallengeAnswer challengeAnswer2 = new ChallengeAnswer(1L, false, "No es cierto", question1);
        question1.setChallenge(challenge);
        User user1 = new User(2L, "Pepe");


        UserQuestion userQuestion = new UserQuestion(2L, user1, question1, challengeAnswer2);
        var sut = userQuestion.isUserRight();

        assertFalse(sut);
    }

    @Test
    void userQuestionHaveAttributesAndMethods() {
        Challenge challenge = new Challenge(1L, "Matematicas");
        Question question1 = new Question();
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true, "Es cierto", question1);
        question1.setChallenge(challenge);
        User user1 = new User(2L, "Pepe");


        UserQuestion userQuestion = new UserQuestion();
        userQuestion.setQuestion(question1);
        userQuestion.setUser(user1);
        userQuestion.setChallengeAnswer(challengeAnswer1);
        userQuestion.setId(2L);
        List<UserQuestion> userQuestionList = new ArrayList<UserQuestion>();
        userQuestionList.add(userQuestion);

        assertEquals(userQuestion.getQuestion(), question1);
        assertEquals(userQuestion.getUser(), user1);
        assertEquals(userQuestion.getChallengeAnswer(), challengeAnswer1);
        assertEquals(userQuestion.getId(), 2L);
        assertEquals(userQuestion.isUserRight(), true);
        assertEquals(userQuestion.correctAnswersCounter(userQuestionList), 1);
        assertTrue(userQuestion.isUserRight());
    }

    @Test
    void isUserRight() {
        Challenge challenge = new Challenge(1L, "Matematicas");
        Question question1 = new Question();
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true, "Es cierto", question1);
        question1.setChallenge(challenge);
        User user1 = new User(2L, "Pepe");


        UserQuestion userQuestion = new UserQuestion(2L, user1, question1, challengeAnswer1);
        List<UserQuestion> userQuestionList = new ArrayList<UserQuestion>();
        userQuestionList.add(userQuestion);

        assertTrue(userQuestion.isUserRight());
    }


}

