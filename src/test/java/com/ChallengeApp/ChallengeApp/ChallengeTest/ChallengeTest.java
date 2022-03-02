package com.ChallengeApp.ChallengeApp.ChallengeTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
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
}
