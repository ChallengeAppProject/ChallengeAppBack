package com.ChallengeApp.ChallengeApp.ServiceTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.ChallengeAppRepository;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.Services.ChallengeSqlServiceImpl;
import com.ChallengeApp.ChallengeApp.Services.QuestionSqlServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ChallengeServiceTest {

    @Mock
    ChallengeAppRepository challengeAppRepository;
    @Mock
    QuestionRepository questionRepository;
    @Test
    void whenSaveChallengeItShouldReturnChallenge(){
        Challenge challenge = new Challenge(1L,"mockChallenge");
        Mockito.when(challengeAppRepository.save(challenge)).thenReturn(challenge);

        var result = challengeAppRepository.save(challenge);


        assertThat(result.getId(),equalTo(1L));
        assertThat(result.getName(), equalTo("mockChallenge"));
        assertThat(result, equalTo(challenge));
    }

        @Test
        void whenSaveChallengeServiceReturnsChallenge() {
            Challenge testChallenge = new Challenge(1L,"mockChallenge");
            Mockito.when(challengeAppRepository.save(testChallenge)).thenReturn(testChallenge);
            var challService = new ChallengeSqlServiceImpl(challengeAppRepository);

            var sut = challService.saveChallenge(testChallenge);

            assertThat(sut.getId(),equalTo(1L));
            verify(challengeAppRepository).save(testChallenge);
        }

    @Test
    void whenDeletingChallengeItShouldReturnAString() {
        Challenge testChallenge = new Challenge(2L,"mockChallenge");
        var challService = new ChallengeSqlServiceImpl(challengeAppRepository);

        challService.delete(2L);

        verify(challengeAppRepository).deleteById(2L);


    }

    @Test
    void whenUpdatingAChallengeTheChallengeIsUpdated(){
        Challenge testChallenge = new Challenge(2L,"testChallenge");
        testChallenge.setName("newTestChallenge");
        Mockito.when(challengeAppRepository.save(testChallenge)).thenReturn(testChallenge);
        var challService = new ChallengeSqlServiceImpl(challengeAppRepository);

        var sut = challService.save(testChallenge);

        assertThat(sut.getName(),equalTo("newTestChallenge"));
    }

    @Test
    void challengeServiceCanGetAllQuestionsByChallengeId(){
        Challenge testChallenge = new Challenge(2L,"testChallenge");
        Question question1 = new Question(1L, "","",testChallenge);
        Question question2 = new Question(2L, "", "",testChallenge);
        List questionList = new ArrayList<Question>();
        questionList.add(question1);
        questionList.add(question2);
       Mockito.when(questionRepository.findAllByChallenge(testChallenge)).thenReturn(questionList);

        var questionService = new QuestionSqlServiceImp(questionRepository);

        var sut = questionService.getAllByChallenge(testChallenge);

        assertEquals(sut.size(),2);

    }


}
