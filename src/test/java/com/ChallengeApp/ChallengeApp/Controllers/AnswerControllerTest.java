package com.ChallengeApp.ChallengeApp.Controllers;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.AnswerRepository;
import com.ChallengeApp.ChallengeApp.Services.AnswerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        Challenge challenge1 = new Challenge();

        Question question1 = new Question();

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

        mockMvc.perform(get("/answers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));


    }

}