package com.ChallengeApp.ChallengeApp.ChallengeControllerTest;

import com.ChallengeApp.ChallengeApp.Controllers.ChallengeController;
import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Services.ChallengeService;
import com.ChallengeApp.ChallengeApp.Services.QuestionService;
import com.ChallengeApp.ChallengeApp.dtos.ChallengeResponseDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChallengeController.class)
class ChallengeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ChallengeService challengeService;
    @MockBean
    QuestionService questionService;

    Challenge challenge1 = new Challenge(1L, "Mates");
    ChallengeResponseDTO challengeResponseDTO = new ChallengeResponseDTO();

    @Test
    public void getByIdShouldReturnTheCorrectChallenge() throws Exception{
        challengeResponseDTO.mapFromChallenge(challenge1);
        when(challengeService.get(1L)).thenReturn(challengeResponseDTO);

        mockMvc.perform(get("/challenges/"+challenge1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo("Mates")));
    }
}
