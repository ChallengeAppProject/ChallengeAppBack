package com.ChallengeApp.ChallengeApp.ServiceTest;

import com.ChallengeApp.ChallengeApp.Models.*;
import com.ChallengeApp.ChallengeApp.Repositories.UserQuestionRepository;
import com.ChallengeApp.ChallengeApp.Repositories.UserRepository;
import com.ChallengeApp.ChallengeApp.Services.UserQuestionService;
import com.ChallengeApp.ChallengeApp.Services.UserQuestionSqlServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserQuestionServiceTest {
    @Mock
    UserQuestionRepository userQuestionRepository;

    @Test
    void userHaveAChallengeTotalScore(){
        User user1 = new User(2L, "Pepe");
        Challenge challenge = new Challenge(1L,"Matematicas");
        Question question1 = new Question();
        Question question2 = new Question();
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true,"Es cierto",question1 );
        ChallengeAnswer challengeAnswer2 = new ChallengeAnswer(2L, false,"Es cierto",question2 );
        question1.setChallenge(challenge);
        question2.setChallenge(challenge);

        UserQuestion userQuestion1 = new UserQuestion(1L,user1, question1, challengeAnswer1);
        UserQuestion userQuestion2 = new UserQuestion(2L,user1, question2, challengeAnswer2);

        List userQuestions = new ArrayList<UserQuestion>();
        userQuestions.add(userQuestion1);
        userQuestions.add(userQuestion2);


        Mockito.when(userQuestionRepository.findAllByUserAndQuestion_Challenge(user1,challenge)).thenReturn(userQuestions);

        UserQuestionSqlServiceImpl userQuestionSqlService = new UserQuestionSqlServiceImpl(userQuestionRepository);


        assertTrue(userQuestion1.isUserRight());
        assertThat(userQuestion1.correctAnswersCounter(userQuestions), equalTo(1));
    }
}