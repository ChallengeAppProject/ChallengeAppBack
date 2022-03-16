package com.ChallengeApp.ChallengeApp.Repositories;

import com.ChallengeApp.ChallengeApp.Models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserQuestionRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    UserQuestionRepository userQuestionRepository;

    @Test
    void findAllByUserAndQuestion_Challenge() {
        Challenge challenge = new Challenge(1L,"Matematicas");
        Question question1 = new Question();
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true,"Es cierto",question1 );
        question1.setChallenge(challenge);
        User user1 = new User(2L, "Pepe");
        UserQuestion userQuestion1 = new UserQuestion(1L, user1,question1,challengeAnswer1);

        testEntityManager.persist(challenge);
        testEntityManager.persist(user1);
        testEntityManager.persist(question1);
        testEntityManager.persist(challengeAnswer1);
        testEntityManager.persist(userQuestion1);

        testEntityManager.flush();


    }


}