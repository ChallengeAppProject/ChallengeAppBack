package com.ChallengeApp.ChallengeApp.ControllersTest;

import com.ChallengeApp.ChallengeApp.Controllers.UserQuestionController;
import com.ChallengeApp.ChallengeApp.Models.*;
import com.ChallengeApp.ChallengeApp.Services.UserQuestionService;
import com.ChallengeApp.ChallengeApp.Services.UserServiceImpl;
import com.ChallengeApp.ChallengeApp.dtos.QuestionListResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.UserQuestionResponseDTO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserQuestionController.class)
public class UserQuestionControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserQuestionService userQuestionService;

    @MockBean
    UserServiceImpl userService;

    @SneakyThrows
    @Test
    void userCanGetTheScoreChallenge(){
        Challenge challenge1 = new Challenge(1L, "Mates");
        Question question = new Question(1L, "img.jpg", "2+2?", challenge1);
        ChallengeAnswer answer = new ChallengeAnswer(1L, true, "4", question);
        ChallengeAnswer answer1 = new ChallengeAnswer(1L, false, "5", question);

        User user = new User();
        UserQuestion userQuestion = new UserQuestion(1L, user, question,answer );
        UserQuestionResponseDTO userQuestionResponseDTO = new UserQuestionResponseDTO().mapFromUserQuestion(userQuestion);



        List<UserQuestion> userQuestionList=new ArrayList<UserQuestion>();
        userQuestionList.add(userQuestion);
        QuestionListResponseDTO questionListResponseDTO = new QuestionListResponseDTO().mapFromQuestionsList(userQuestionList);

        when(userService.getAuthenticatedUser()).thenReturn(user);
        when(userQuestionService.findAllByUserAndQuestion_Challenge(user, challenge1)).thenReturn(questionListResponseDTO);

            var sut = mockMvc.perform(get("/userQuestion/challenge/1"))
                                            .andDo(print())
                                            .andExpect(status().isOk())
                                            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                            .andReturn().getResponse();

        }
    }

