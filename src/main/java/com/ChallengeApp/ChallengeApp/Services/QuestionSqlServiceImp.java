package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Long;
import java.util.List;

@Service
public class QuestionSqlServiceImp implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

   @Override
    public Question get(Long id)  {return questionRepository.findById(id).get();}

    @Override
    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    @Override
    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }
}