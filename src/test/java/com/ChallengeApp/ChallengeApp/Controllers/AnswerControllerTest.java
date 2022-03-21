package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.AnswerRepository;
import com.ChallengeApp.ChallengeApp.Services.AnswerService;
import com.ChallengeApp.ChallengeApp.dtos.AnswerRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
class AnswerControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    AnswerService answerService;

    @Mock
    AnswerRepository answerRepository;

    @Test
    void getAllMethodShouldReturnAListOfAnswers() throws Exception {
/*
        Challenge challenge1 = new Challenge();

        Question question1 = new Question(1L, "ImgUrl", "Qué hora es?", challenge1);

        question1.setChallenge(challenge1);

        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer();
        ChallengeAnswer challengeAnswer2 = new ChallengeAnswer();

        challengeAnswer1.setQuestion(question1);
        challengeAnswer2.setQuestion(question1);

        Mockito.when(answerRepository.save(challengeAnswer1)).thenReturn(challengeAnswer1);
        Mockito.when(answerRepository.save(challengeAnswer2)).thenReturn(challengeAnswer2);

        List answerList = new ArrayList<ChallengeAnswer>();
        answerList.add(challengeAnswer1);
        answerList.add(challengeAnswer2);

        when(answerService.getAllAnswer()).thenReturn(answerList);

        mockMvc.perform(get("/answers/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));


    }


    @Test
    public void PostANewAnswerReturnsConfirmationMessage() throws Exception {

        Challenge challenge1 = new Challenge(1L, "testChallenge1");

        Question question1 = new Question(1L, "img1.jpg", "This is useful?", challenge1);

        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true, "Si", question1);

        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO();
        answerResponseDTO.mapFromAnswer(challengeAnswer1);
        AnswerRequestDTO answerRequestDTO = new AnswerRequestDTO();

        answerService.saveAnswer(answerRequestDTO);

        var messageExpected = "New answer created";


        when(answerService.createAnswer(answerRequestDTO)).thenReturn(answerResponseDTO);
        when(answerService.saveAnswer(answerRequestDTO)).thenReturn(messageExpected);


        var sut = mockMvc.perform(post("/answers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(messageExpected, sut);
    }*/

    }
}
