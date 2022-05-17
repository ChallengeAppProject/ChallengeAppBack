package com.ChallengeApp.ChallengeApp.RepositoriesTest;

import com.ChallengeApp.ChallengeApp.Models.*;
import com.ChallengeApp.ChallengeApp.Repositories.UserQuestionRepository;
import com.ChallengeApp.ChallengeApp.Services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@DataJpaTest
class UserQuestionRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    UserQuestionRepository userQuestionRepository;
    @MockBean
    UserService userService;

    @Test
    void findAllByUserAndQuestion_Challenge() {
        Challenge challenge = new Challenge();
        testEntityManager.persist(challenge);
        Question question1 = new Question();
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer();
        testEntityManager.persist(challengeAnswer1);
        User user1 = new User();
        user1.setUsername("Pepe");

        when(userService.getAuthenticatedUser()).thenReturn(user1);
        UserQuestion userQuestion1 = new UserQuestion();


        testEntityManager.persist(user1);
        testEntityManager.persist(question1);
        testEntityManager.persist(userQuestion1);

        testEntityManager.flush();

        assertThat(user1.getUsername(), equalTo("Pepe"));


    }


}