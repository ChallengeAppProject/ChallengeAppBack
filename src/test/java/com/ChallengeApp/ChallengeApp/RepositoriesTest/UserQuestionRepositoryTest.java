package com.ChallengeApp.ChallengeApp.RepositoriesTest;

import com.ChallengeApp.ChallengeApp.Models.*;
import com.ChallengeApp.ChallengeApp.Repositories.UserQuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
@DataJpaTest
class UserQuestionRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    UserQuestionRepository userQuestionRepository;

    @Test
    void findAllByUserAndQuestion_Challenge() {
        Challenge challenge = new Challenge();
        testEntityManager.persist(challenge);
        Question question1 = new Question();
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer();
        User user1 = new User();
        user1.setUsername("Pepe");
        UserQuestion userQuestion1 = new UserQuestion();


        testEntityManager.persist(user1);
        testEntityManager.persist(question1);
        testEntityManager.persist(challengeAnswer1);
        testEntityManager.persist(userQuestion1);

        testEntityManager.flush();

        assertThat(user1.getUsername(), equalTo("Pepe"));


    }


}