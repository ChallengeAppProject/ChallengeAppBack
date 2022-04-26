package com.ChallengeApp.ChallengeApp.ControllersTest;

import com.ChallengeApp.ChallengeApp.Controllers.AnswerController;
import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.RoleRepository;
import com.ChallengeApp.ChallengeApp.Repositories.UserRepository;
import com.ChallengeApp.ChallengeApp.Services.AnswerService;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AnswerController.class)
class AnswerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    AnswerService answerService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    RoleRepository roleRepository;
    @Test
    void getAllMethodShouldReturnAListOfAnswers() throws Exception {
        Challenge challenge1 = new Challenge(1L, "Horario");

        Question question1 = new Question(1L, "ImgUrl", "Qué hora es?", challenge1);

        question1.setChallenge(challenge1);

        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true, "la hora de comer", question1);
        ChallengeAnswer challengeAnswer2 = new ChallengeAnswer(1L, false, "la hora de dormir", question1);

        challengeAnswer1.setQuestion(question1);
        challengeAnswer2.setQuestion(question1);

        List<ChallengeAnswer> answerList = new ArrayList<ChallengeAnswer>();
        answerList.add(challengeAnswer1);
        answerList.add(challengeAnswer2);

        List<AnswerResponseDTO> answerResponseDTOList = new ArrayList<AnswerResponseDTO>();

        for (ChallengeAnswer answer : answerList) {
            AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO();
            answerResponseDTO.mapFromAnswer(answer);
            answerResponseDTOList.add(answerResponseDTO);
        }

        when(answerService.getAllAnswer()).thenReturn(answerResponseDTOList);

        mockMvc.perform(get("/answers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].textAnswer", Matchers.equalTo("la hora de comer")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].correctAnswer", Matchers.equalTo(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));


    }

}

