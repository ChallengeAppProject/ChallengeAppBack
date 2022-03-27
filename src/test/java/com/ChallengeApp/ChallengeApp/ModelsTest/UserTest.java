package com.ChallengeApp.ChallengeApp.ModelsTest;

import com.ChallengeApp.ChallengeApp.Models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void userHaveIdAndUserName(){

        User user = new User();
        user.setUserName("Juani");
        user.setId(1L);

        assertEquals(user.getUserName(),"Juani");
        assertEquals(user.getId(), 1L);

    }
}
