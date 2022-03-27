package com.ChallengeApp.ChallengeApp.ModelsTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

public class ChallengeTest {
    @Test

    void challengeHaveIdAndName() {
        Challenge challenge = new Challenge(1L,"prueba");

        assertThat(challenge, hasProperty("id"));
        assertThat(challenge, hasProperty("name"));
        assertThat(challenge.getName(), equalTo("prueba"));

    }

    @Test
    void challengeHasID() {
        Challenge challenge = new Challenge(1L,"prueba");
        Challenge challenge2 = new Challenge(2L,"prueba");

        assertThat(challenge.getId(), equalTo((long)1));
        assertThat(challenge2.getId(), equalTo((long)2));


    }
}
