package com.ChallengeApp.ChallengeApp.ServiceTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.Services.ChallengeSqlServiceImpl;
import com.ChallengeApp.ChallengeApp.Services.QuestionSqlServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;


@SpringBootTest
public class QuestionServiceTest {
    @Mock
    QuestionRepository questionRepository;

    @Test
    void questionServiceCanSaveAQuestion(){
        Challenge challenge = new Challenge(1L, "ciencias");
        Question question = new Question(1L,"img.jpg","This is useful?",challenge);
        var questionSqlServiceImp = new QuestionSqlServiceImp(questionRepository);
        Mockito.when(questionRepository.save(question)).thenReturn(question);

        var sut = questionSqlServiceImp.saveQuestion(question);

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


}
