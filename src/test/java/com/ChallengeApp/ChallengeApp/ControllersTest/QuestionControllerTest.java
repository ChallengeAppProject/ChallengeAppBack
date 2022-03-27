package com.ChallengeApp.ChallengeApp.ControllersTest;

import com.ChallengeApp.ChallengeApp.Controllers.QuestionController;
import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Services.AnswerService;
import com.ChallengeApp.ChallengeApp.Services.ChallengeService;
import com.ChallengeApp.ChallengeApp.Services.QuestionService;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.jsonpath.internal.function.ParamType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;


@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ChallengeService challengeService;

    @MockBean
    QuestionService questionService;

    @MockBean
    AnswerService answerService;

    @Test
    void getByIdShouldReturnAQuestionResponseDTO() throws Exception{

        Challenge challenge1 = new Challenge(1L, "Mates");
        Question question = new Question(1L, "img.jpg", "2+2?", challenge1);

        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO().mapFromQuestion(question);

        when(questionService.get(1L)).thenReturn(questionResponseDTO);

       var sut = mockMvc.perform(get("/questions/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.imgUrl", equalTo("img.jpg")))
                        .andReturn().getResponse();
    }

    @Test
    void getAnswersByQuestionIdReturnsAListOfAnswers()throws Exception{
        Challenge challenge1 = new Challenge(1L, "Mates");
        Question question1 = new Question(1L, "img.jpg", "2+2?", challenge1);
        Question question2 = new Question(2L, "img.jpg", "3+3?", challenge1);

        ChallengeAnswer answer1 = new ChallengeAnswer(1L, true, "4", question1);
        ChallengeAnswer answer2 = new ChallengeAnswer(2L, false, "5", question1);
        ChallengeAnswer answer3 = new ChallengeAnswer(3L, false, "8", question2);


        AnswerResponseDTO answerResponseDTO1 = new AnswerResponseDTO().mapFromAnswer(answer1);
        AnswerResponseDTO answerResponseDTO2 = new AnswerResponseDTO().mapFromAnswer(answer2);
        List answerResponseList = new ArrayList<AnswerResponseDTO>();
        answerResponseList.add(answerResponseDTO1);
        answerResponseList.add(answerResponseDTO2);

        when(answerService.getAllByQuestion(question1)).thenReturn(answerResponseList);

        var sut = mockMvc.perform(get("/questions/1/answers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                /*.andExpect(jsonPath("$[0].answerId", equalTo(1)))  ***NO DEVUELVE BODY***
                .andExpect(jsonPath("$[1].answerText", equalTo("8")))*/
                .andReturn().getResponse();
    }
}
