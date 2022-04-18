package com.ChallengeApp.ChallengeApp.ModelsTest;

import com.ChallengeApp.ChallengeApp.Models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void userHaveIdAndUserName(){

        User user = new User();
        user.setUsername("Juani");
        user.setId(1L);

        assertEquals(user.getUsername(),"Juani");
        assertEquals(user.getId(), 1L);

    }
}
