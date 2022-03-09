package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.AnswerRepository;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AnswerSqlServiceImpl implements AnswerService{
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<ChallengeAnswer> getAllAnswer(){
        return answerRepository.findAll();
    }

    @Override
    public ChallengeAnswer saveAnswer(ChallengeAnswer challengeAnswer){
        return answerRepository.save(challengeAnswer);
    }

    @Override
    public ChallengeAnswer get(Long id)  {return answerRepository.findById(id).get();}

    @Override
    public void delete (Long id){answerRepository.deleteById(id);
    }
    @Override
    public ChallengeAnswer save(ChallengeAnswer challengeAnswer) {
        return answerRepository.save(challengeAnswer);
    }
}

