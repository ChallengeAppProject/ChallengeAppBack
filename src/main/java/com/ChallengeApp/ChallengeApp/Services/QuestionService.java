package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;

import java.util.List;

public interface QuestionService {

    public Question get(Long id);

    List<Question> getAllQuestion();

    public Question saveQuestion(Question question);


    public void delete (Long id);

    public Question save (Question question);

}
