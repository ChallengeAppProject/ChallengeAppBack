package com.ChallengeApp.ChallengeApp.ServiceTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.Services.ChallengeSqlServiceImpl;
import com.ChallengeApp.ChallengeApp.Services.QuestionSqlServiceImp;
import com.ChallengeApp.ChallengeApp.dtos.ChallengeResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


@SpringBootTest
public class QuestionServiceTest {
    @Mock
    QuestionRepository questionRepository;

    @Test
    void questionServiceCanSaveAQuestion(){
        Challenge challenge = new Challenge(1L, "ciencias");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO().mapFromQuestion(question);
        var questionSqlServiceImp = new QuestionSqlServiceImp(questionRepository);
        Mockito.when(questionRepository.save(question)).thenReturn(questionResponseDTO);

        var sut = questionSqlServiceImp.saveQuestion(questionResponseDTO);

        assertThat(question, equalTo(sut));
        assertThat(question.getChallengeQuestion(), equalTo("This is useful?"));
        verify(questionRepository).save(question);
    }

    @Test
    void questionServiceCanReturnAQuestionById(){
        Challenge challenge = new Challenge(1L, "ciencias");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        var questionSqlServiceImp = new QuestionSqlServiceImp(questionRepository);

        var sut = questionSqlServiceImp.get(1L);

        assertThat(question, equalTo(sut));
        assertThat(question.getChallengeQuestion(), equalTo("This is useful?"));
        verify(questionRepository).findById(1L);
    }

    @Test
    void questionServiceCanUpdateAQuestion(){
        Challenge challenge = new Challenge(2L,"testChallenge");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        Mockito.when(questionRepository.save(question)).thenReturn(question);
        question.setChallengeQuestion("Sure is useful?");
        var questionSqlServiceImp = new QuestionSqlServiceImp(questionRepository);

        var sut = questionSqlServiceImp.save(question);

        assertThat(sut.getChallengeQuestion(),equalTo("Sure is useful?"));
    }

    @Test
    void questionServiceCanDeleteAQuestion(){
        Challenge challenge = new Challenge(2L,"testChallenge");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        var questionSqlServiceImp = new QuestionSqlServiceImp(questionRepository);

         questionSqlServiceImp.delete(1L);

         verify(questionRepository).deleteById(1L);
    }

    @Test
    void questionServiceCanGetAllQuestionsByChallengeId(){
        Challenge testChallenge = new Challenge(2L,"testChallenge");
        Question question1 = new Question(1L, "","",testChallenge);
        Question question2 = new Question(2L, "", "",testChallenge);
        List questionList = new ArrayList<Question>();
        questionList.add(question1);
        questionList.add(question2);
        Mockito.when(questionRepository.findAllByChallenge(testChallenge)).thenReturn(questionList);

        var questionService = new QuestionSqlServiceImp(questionRepository);

        var sut = questionService.getAllByChallenge(testChallenge);

        assertEquals(sut.size(),2);

    }

    @Test
    void questionServiceCanReturnAListOfQuestionsByChallengeIdUsingADTOResponse(){
        //GIVEN - ARRANGE
        //creamos un challenge y 2 questions
        Challenge challenge1 = new Challenge(1L,"testChallenge1");

        Question question1 = new Question(1L,"img1.jpg","This is useful?",challenge1);
        Question question2 = new Question(2L,"img2.jpg","",challenge1);
        //añadimos las questions a una lista
        List<Question> questionList = new ArrayList<Question>();
        questionList.add(question1);
        questionList.add(question2);
        //Creamos una lista de DTO's
        List<QuestionResponseDTO> questionResponseDTOList = new ArrayList<QuestionResponseDTO>();
        //Mediante un bucle forEach de la lista de questions añadimos un DTO a la lista de DTO's
        // y seteamos sus atributos con la función .mapFromQuestion de la clase QuestionResponseDTO
        for(Question question : questionList){
            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
            questionResponseDTO.mapFromQuestion(question);
            questionResponseDTOList.add(questionResponseDTO);
        }

        //mediante Mockito pedimos que cuando se use la función .findAllByChallenge del repositorio mockeado
        // nos devuelva la lista de Questions
        Mockito.when(questionRepository.findAllByChallenge(challenge1)).thenReturn(questionList);
        //Creamos una implementación del servicio
        QuestionSqlServiceImp questionSqlServiceImp = new QuestionSqlServiceImp(questionRepository);

        //WHEN - ACT
        // Utilizamos la función getAllByChallenges del servicio (la que usa la .findAllByChallenge del repo);

        var sut = questionSqlServiceImp.getAllByChallenge(challenge1);

        //THEN - ASSERT
        assertEquals(questionResponseDTOList.size(), sut.size() );
        assertThat(sut.get(0).getChallengeId(), equalTo(1L));
        assertThat(questionResponseDTOList.get(0).getChallengeId(), equalTo(1L));
    }

}
