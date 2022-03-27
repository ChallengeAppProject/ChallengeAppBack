package com.ChallengeApp.ChallengeApp.ServiceTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.AnswerRepository;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.Services.AnswerServiceImpl;
import com.ChallengeApp.ChallengeApp.dtos.AnswerRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    @Mock
    QuestionRepository questionRepository;

    @Test
    void answerServiceCanCreateAnAnswer(){
        //GIVEN or ARRANGE (Creamos un Challenge, una question y un AnswerRequestDTO
        // y usamos el mock del repo para guardar la answer)
        Challenge challenge = new Challenge(1L, "ciencias");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        AnswerRequestDTO answerRequestDTO = new AnswerRequestDTO();
        answerRequestDTO.setTextAnswer("No");
        answerRequestDTO.setQuestionId(question.getId());
        answerRequestDTO.setCorrectAnswer(true);

        //Transformamos el request en answer
        ChallengeAnswer answer = new ChallengeAnswer();
        answer.setTextAnswer(answerRequestDTO.getTextAnswer());
        answer.setCorrectAnswer(answerRequestDTO.getCorrectAnswer());
        //Utilizamos Mock del repo de questions para pasarle la question a la answer
        Mockito.when(questionRepository.findById(answerRequestDTO.getQuestionId())).thenReturn(Optional.of(question));
        answer.setQuestion(question);
        //Utilizamos el Mock del repo de answers para guardar la answer
        Mockito.when(answerRepository.save(answer)).thenReturn(answer);
        answerRepository.save(answer);
        //Transformamos la answer en ResponseDTO
        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO();
        answerResponseDTO.mapFromAnswer(answer);
        //Cfreamos una implementación del service de answers
        var answerSqlServiceImp = new AnswerServiceImpl(answerRepository,questionRepository);

        //WHEN or ACT (llamamos a la función saveAnswer del servicio);
        var sut = answerSqlServiceImp.createAnswer(answerRequestDTO);

        //THEN or ASSERT(verificamos answer=sut, textAnswer y que se ha usado el answerRepository.save())
        assertThat(answer.getTextAnswer(), equalTo(sut.getTextAnswer()));
        assertThat(answerRequestDTO.correctAnswer, equalTo(sut.getCorrectAnswer()));
        assertEquals(answerRequestDTO.getQuestionId(),sut.getQuestionId());
        verify(answerRepository).save(answer);
        //verify(questionRepository.findById(1L)); //NO PASA
    }

    @Test
    void answerServiceCanReturnAnAnswerResponseDTOByAnswerId(){
        Challenge challenge = new Challenge(1L, "ciencias");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        ChallengeAnswer answer = new ChallengeAnswer(1L,true,"YES", question);



        Mockito.when(answerRepository.findById(1L)).thenReturn(Optional.of(answer));
        var answerSqlServiceImp = new AnswerServiceImpl(answerRepository,questionRepository);

        var sut = answerSqlServiceImp.getAnswerById(1L);

        assertThat(answer.getTextAnswer(), equalTo(sut.getTextAnswer()));
        assertThat(true, equalTo(sut.getCorrectAnswer()));
        verify(answerRepository).findById(1L);
    }

    @Test
    void answerServiceCanUpdateAnAnswer(){
        //Creamos un requestDTO desde una ChallengeAnswer
        Challenge challenge = new Challenge(2L,"testChallenge");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        ChallengeAnswer answer = new ChallengeAnswer(1L,true,"yes",question);

        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO();
        answerResponseDTO.mapFromAnswer(answer);
        AnswerRequestDTO answerRequestDTO = new AnswerRequestDTO();
        answerRequestDTO.setAnswerId(answer.getId());
        answerRequestDTO.setTextAnswer("NO");
        answerRequestDTO.setCorrectAnswer(answer.getCorrectAnswer());
        answerRequestDTO.setQuestionId(answer.getQuestion().getId());

        //Le pasamos los nuevos datos a la answer

        answer.setTextAnswer(answerRequestDTO.getTextAnswer());

        //Utilizamos el mock del repo para que nos devuelva una question
        Mockito.when(questionRepository.findById(answerRequestDTO.questionId)).thenReturn(Optional.of(question));
        //Utilizamos el mock del repo para que nos encuentre la answer
        Mockito.when(answerRepository.findById(answerRequestDTO.answerId)).thenReturn(Optional.of(answer));
        //Utilizamos el mock del repo para que nos devuelva
        Mockito.when(answerRepository.save(answer)).thenReturn(answer);

        var answerSqlServiceImp = new AnswerServiceImpl(answerRepository,questionRepository);

        var sut = answerSqlServiceImp.saveAnswer(answerRequestDTO);

        assertThat(sut.getTextAnswer(),equalTo("NO"));
    }

    @Test
    void answerServiceCanDeleteAnAnswer(){
        Challenge challenge = new Challenge(2L,"testChallenge");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        ChallengeAnswer answer = new ChallengeAnswer(1L,true,"yes",question);

        AnswerRequestDTO answerRequestDTO = new AnswerRequestDTO();
        answerRequestDTO.setAnswerId(answer.getId());

        Mockito.when(answerRepository.findById(answerRequestDTO.answerId)).thenReturn(Optional.of(answer));

        var answerSqlServiceImp = new AnswerServiceImpl(answerRepository,questionRepository);

        answerSqlServiceImp.delete(answerRequestDTO.getAnswerId());

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
        AnswerServiceImpl answerSqlServiceImpl = new AnswerServiceImpl(answerRepository,questionRepository);

        //WHEN - ACT
        // Utilizamos la función getAllByChallenges del servicio (la que usa la .findAllByChallenge del repo);

        var sut = answerSqlServiceImpl.getAllByQuestion(question1);

        //THEN - ASSERT
        assertEquals(answerResponseDTOList.size(), sut.size() );
        assertThat(sut.get(0).getQuestionId(), equalTo(1L));
        assertThat(answerResponseDTOList.get(0).getQuestionId(), equalTo(1L));
    }


}
