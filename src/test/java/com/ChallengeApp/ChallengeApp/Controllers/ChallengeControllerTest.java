package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Repositories.ChallengeAppRepository;
import com.ChallengeApp.ChallengeApp.Services.AnswerSqlServiceImpl;
import com.ChallengeApp.ChallengeApp.Services.ChallengeService;
import com.ChallengeApp.ChallengeApp.Services.ChallengeSqlServiceImpl;
import com.ChallengeApp.ChallengeApp.Services.QuestionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChallengeController.class)
class ChallengeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ChallengeService challengeService;

    //?¿?¿?wtf
    @MockBean
    QuestionService questionService;

    @Mock
    ChallengeAppRepository challengeAppRepository;

    @Test
    public void getAllMethodShouldReturnAListOfChallenges() throws Exception {

        Challenge challenge1 = new Challenge(1L, "Arte");
        Challenge challenge2 = new Challenge(2L, "Matematicas");
        Challenge challenge3 = new Challenge(3L, "Ciencias");


        Mockito.when(challengeAppRepository.save(challenge1)).thenReturn(challenge1);
        Mockito.when(challengeAppRepository.save(challenge2)).thenReturn(challenge2);
        Mockito.when(challengeAppRepository.save(challenge3)).thenReturn(challenge3);



        List challengeList = new ArrayList<Challenge>();
        challengeList.add(challenge1);
        challengeList.add(challenge2);
        challengeList.add(challenge3);


        when(challengeService.getAllChallenges()).thenReturn(challengeList);




        mockMvc.perform(get("/challenges"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Arte")))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }


    @Test
    void whenCreatingChallengeServerResponseIsOk() throws Exception {

        Challenge challenge1 = new Challenge(5L,"Ciencias");

        when(challengeAppRepository.save(challenge1)).thenReturn(challenge1);
        var url = "/challenges/5";


        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());

    }


}