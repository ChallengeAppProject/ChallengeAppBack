package com.ChallengeApp.ChallengeApp.ServiceTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Repositories.ChallengeAppRepository;
import com.ChallengeApp.ChallengeApp.Services.ChallengeService;
import com.ChallengeApp.ChallengeApp.Services.ChallengeSqlServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class ChallengeServiceTest {

    @Mock
    ChallengeAppRepository challengeAppRepository;
    @Test
    void whenSaveChallengeItShouldReturnChallenge(){
        Challenge challenge = new Challenge(1L,"mockChallenge");
        Mockito.when(challengeAppRepository.save(challenge)).thenReturn(challenge);
        var result = challengeAppRepository.save(challenge);


        assertThat(result.getId(),equalTo(1L));
        assertThat(result.getName(), equalTo("mockChallenge"));
        assertThat(result, equalTo(challenge));




    }
}
