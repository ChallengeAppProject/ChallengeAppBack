package com.ChallengeApp.ChallengeApp.ServiceTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.AnswerRepository;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.Services.AnswerSqlServiceImpl;
import com.ChallengeApp.ChallengeApp.Services.QuestionSqlServiceImp;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AnswerServiceTest {

    @Mock
    AnswerRepository answerRepository;

    @Test
    void answerServiceCanSaveAQuestion(){
        //GIVEN or ARRANGE (Creamos un Challenge, una question y una answer y usamos el mock del repo para guardar la answer)
        Challenge challenge = new Challenge(1L, "ciencias");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        ChallengeAnswer answer = new ChallengeAnswer(1L,true,"yes",question);
        Mockito.when(answerRepository.save(answer)).thenReturn(answer);
        var answerSqlServiceImp = new AnswerSqlServiceImpl(answerRepository);

        //WHEN or ACT (llamamos a la función saveAnswer del servicio);
        var sut = answerSqlServiceImp.saveAnswer(answer);

        //THEN or ASSERT(verificamos answer=sut, textAnswer y que se ha usado el answerRepository.save())
        assertThat(answer, equalTo(sut));
        assertThat(answer.getTextAnswer(), equalTo("yes"));
        verify(answerRepository).save(answer);
    }

    @Test
    void answerServiceCanReturnAQuestionById(){
        Challenge challenge = new Challenge(1L, "ciencias");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        ChallengeAnswer answer = new ChallengeAnswer(1L,true,"yes",question);
        Mockito.when(answerRepository.findById(1L)).thenReturn(Optional.of(answer));
        var answerSqlServiceImp = new AnswerSqlServiceImpl(answerRepository);

        var sut = answerSqlServiceImp.get(1L);

        assertThat(answer, equalTo(sut));
        assertThat(answer.getTextAnswer(), equalTo("yes"));
        verify(answerRepository).findById(1L);
    }

    @Test
    void answerServiceCanUpdateAQuestion(){
        Challenge challenge = new Challenge(2L,"testChallenge");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        ChallengeAnswer answer = new ChallengeAnswer(1L,true,"yes",question);
        Mockito.when(answerRepository.save(answer)).thenReturn(answer);
        answer.setTextAnswer("No");
        var answerSqlServiceImp = new AnswerSqlServiceImpl(answerRepository);

        var sut = answerSqlServiceImp.save(answer);

        assertThat(sut.getTextAnswer(),equalTo("No"));
    }

    @Test
    void answerServiceCanDeleteAQuestion(){
        Challenge challenge = new Challenge(2L,"testChallenge");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        ChallengeAnswer answer = new ChallengeAnswer(1L,true,"yes",question);
        var answerSqlServiceImp = new AnswerSqlServiceImpl(answerRepository);

        answerSqlServiceImp.delete(1L);

        verify(answerRepository).deleteById(1L);
    }
    @Test
    void answerServiceCanReturnAListOfAnswersByQuestionIdUsingADTOResponse(){
        //GIVEN - ARRANGE
        //creamos un challenge y 2 questions
        Challenge challenge1 = new Challenge(1L,"testChallenge1");

        Question question1 = new Question(1L,"img1.jpg","This is useful?",challenge1);
        Question question2 = new Question(2L,"img2.jpg","",challenge1);

        ChallengeAnswer answer1 = new ChallengeAnswer(1L,true, "la tierra es plana", question1);
        ChallengeAnswer answer2 = new ChallengeAnswer(2L,false, "la tierra es redonda", question1);
        //añadimos las questions a una lista
        List<ChallengeAnswer> answerList = new ArrayList<ChallengeAnswer>();
        answerList.add(answer1);
        answerList.add(answer2);
        //Creamos una lista de DTO's
        List<AnswerResponseDTO> answerResponseDTOList = new ArrayList<AnswerResponseDTO>();
        //Mediante un bucle forEach de la lista de questions añadimos un DTO a la lista de DTO's
        // y seteamos sus atributos con la función .mapFromQuestion de la clase QuestionResponseDTO
        for(ChallengeAnswer answer : answerList){
            AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO();
            answerResponseDTO.mapFromAnswer(answer);
            answerResponseDTOList.add(answerResponseDTO);
        }

        //mediante Mockito pedimos que cuando se use la función .findAllByChallenge del repositorio mockeado
        // nos devuelva la lista de Questions
        Mockito.when(answerRepository.findAllByQuestion(question1)).thenReturn(answerList);
        //Creamos una implementación del servicio
        AnswerSqlServiceImpl answerSqlServiceImpl = new AnswerSqlServiceImpl(answerRepository);

        //WHEN - ACT
        // Utilizamos la función getAllByChallenges del servicio (la que usa la .findAllByChallenge del repo);

        var sut = answerSqlServiceImpl.getAllByQuestion(question1);

        //THEN - ASSERT
        assertEquals(answerResponseDTOList.size(), sut.size() );
        assertThat(sut.get(0).getQuestionId(), equalTo(1L));
        assertThat(answerResponseDTOList.get(0).getQuestionId(), equalTo(1L));
    }


}
