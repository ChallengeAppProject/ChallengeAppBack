package com.ChallengeApp.ChallengeApp.ServiceTest;

import com.ChallengeApp.ChallengeApp.Models.*;
import com.ChallengeApp.ChallengeApp.Repositories.*;
import com.ChallengeApp.ChallengeApp.Services.UserQuestionServiceImpl;
import com.ChallengeApp.ChallengeApp.dtos.QuestionListResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.UserQuestionRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.UserQuestionResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserQuestionServiceTest {
    @Mock
    UserQuestionRepository userQuestionRepository;
    @Mock
    AnswerRepository answerRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    QuestionRepository questionRepository;
    @Mock
    ChallengeAppRepository challengeAppRepository;

    @Test
    void userHaveAChallengeTotalScore(){
        User user1 = new User(2L, "Pepe");
        Challenge challenge = new Challenge(1L,"Matematicas");
        Question question1 = new Question();
        Question question2 = new Question();
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true,"Es cierto",question1 );
        ChallengeAnswer challengeAnswer2 = new ChallengeAnswer(2L, false,"No es cierto",question1 );
        question1.setChallenge(challenge);
        question2.setChallenge(challenge);

        UserQuestion userQuestion1 = new UserQuestion(1L,user1, question1, challengeAnswer1);
        UserQuestion userQuestion2 = new UserQuestion(2L,user1, question2, challengeAnswer2);

        List userQuestions = new ArrayList<UserQuestion>();
        userQuestions.add(userQuestion1);
        userQuestions.add(userQuestion2);


        Mockito.when(userQuestionRepository.findAllByUserAndQuestion_Challenge(user1,challenge)).thenReturn(userQuestions);

        UserQuestionServiceImpl userQuestionSqlService = new UserQuestionServiceImpl(userQuestionRepository, answerRepository,
                userRepository, questionRepository, challengeAppRepository);


        assertTrue(userQuestion1.isUserRight());
        assertThat(userQuestion1.correctAnswersCounter(userQuestions), equalTo(1));
    }

   @Test
    void userQuestionQuestionsCanReturnTheChallengeTheyBelongTo() {
        Challenge challenge1 = new Challenge(1L,"Programacion");
        Question question1 = new Question();
        question1.setChallenge(challenge1);
        User user = new User(3L,"Carmen");
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L,false, "Es correcto",question1);
        UserQuestion userQuestion = new UserQuestion(1L,user,question1,challengeAnswer1);

        var sut = userQuestion.challenge();

        assertThat(sut, equalTo(challenge1));
    }

    @Test
    void userQuestionServiceReturnsUserQuestionResponseDTO() {
        User user1 = new User(2L, "Pepe");
        Challenge challenge = new Challenge(1L,"Matematicas");
        Question question1 = new Question(1L, "img.jpg","es cierto eso?", challenge);
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true,"Sí",question1 );

        question1.setChallenge(challenge);


        UserQuestion userQuestion1 = new UserQuestion(1L,user1, question1, challengeAnswer1);

        UserQuestionRequestDTO userQuestionReq = new UserQuestionRequestDTO();
        userQuestionReq.setUserId(user1.getId());
        userQuestionReq.setChallengeId(challenge.getId());
        userQuestionReq.setQuestionId(question1.getId());
        userQuestionReq.setChallengeAnswerId(challengeAnswer1.getId());

        var userQuestionResponse = new UserQuestionResponseDTO().mapFromUserQuestion(userQuestion1);

        UserQuestionServiceImpl userQuestionService = new UserQuestionServiceImpl(userQuestionRepository, answerRepository, userRepository, questionRepository,challengeAppRepository);

        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(user1));
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question1));
        Mockito.when(answerRepository.findById(1L)).thenReturn(Optional.of(challengeAnswer1));
        Mockito.when(userQuestionRepository.save(userQuestion1)).thenReturn(userQuestion1);



        var sut =userQuestionService.save(userQuestionReq);
        assertThat(userQuestionResponse.getUser(), samePropertyValuesAs(sut.getUser()));
        assertThat(userQuestionResponse.getChallenge(), samePropertyValuesAs(sut.getChallenge()));
        assertThat(userQuestionResponse.getQuestion(), samePropertyValuesAs(sut.getQuestion()));
        assertThat(userQuestionResponse.getChallengeAnswer(), samePropertyValuesAs(sut.getChallengeAnswer()));
        assertThat(userQuestionResponse.getCorrectAnswers(), samePropertyValuesAs(sut.getCorrectAnswers()));

    }

    @Test
    void userQuestionServiceCanReturnAUserQuestionList(){

        User user1 = new User(2L, "Pepe");
        Challenge challenge = new Challenge(1L,"Matematicas");
        Question question1 = new Question(1L, "img.jpg","es cierto eso?", challenge);
        Question question2 = new Question(2L, "img.jpg","es falso eso?", challenge);
        ChallengeAnswer challengeAnswer1 = new ChallengeAnswer(1L, true,"Sí",question1 );
        ChallengeAnswer challengeAnswer2 = new ChallengeAnswer(1L, true,"No",question2 );
        question1.setChallenge(challenge);
        question2.setChallenge(challenge);


        UserQuestion userQuestion1 = new UserQuestion(1L,user1, question1, challengeAnswer1);
        UserQuestion userQuestion2 = new UserQuestion(2L,user1, question2, challengeAnswer2);

        List userQuestions = new ArrayList<UserQuestion>();
        userQuestions.add(userQuestion1);
        userQuestions.add(userQuestion2);

        UserQuestionServiceImpl userQuestionService = new UserQuestionServiceImpl(userQuestionRepository,
                answerRepository, userRepository, questionRepository,challengeAppRepository);


        Mockito.when(userQuestionRepository.findAllByUserAndQuestion_Challenge(user1, challenge)).thenReturn(userQuestions);

        QuestionListResponseDTO questionListResponseDTO = new QuestionListResponseDTO();
        questionListResponseDTO.mapFromQuestionsList(userQuestions);

        var sut =userQuestionService.getAllfindAllByUserAndQuestion_Challenge(user1,challenge);

        assertEquals(sut.getUserQuestionList(), questionListResponseDTO.getUserQuestionList());
        assertEquals(sut.getCorrectAnswers(), questionListResponseDTO.getCorrectAnswers());
        assertEquals(sut.getIncorrectAnswers(), questionListResponseDTO.getIncorrectAnswers());
        assertThat(sut.getChallenge(), samePropertyValuesAs(questionListResponseDTO.getChallenge()));
        assertThat(sut.getUser(), samePropertyValuesAs(questionListResponseDTO.getUser()));
    }
}
