package com.ChallengeApp.ChallengeApp.ServiceTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.AnswerRepository;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.Services.AnswerSqlServiceImpl;
import com.ChallengeApp.ChallengeApp.Services.QuestionSqlServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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

}
