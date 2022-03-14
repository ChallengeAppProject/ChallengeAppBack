package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.AnswerRepository;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class AnswerSqlServiceImpl implements AnswerService{
    @Autowired
    private AnswerRepository answerRepository;

    public AnswerSqlServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

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

    @Override
    public List<AnswerResponseDTO> getAllByQuestion(Question question) {

        List<ChallengeAnswer> answerList = answerRepository.findAllByQuestion(question);

        List<AnswerResponseDTO> answerResponseDTOList = new ArrayList<AnswerResponseDTO>();

        for(ChallengeAnswer answer :answerList){
            AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO();
            answerResponseDTO.mapFromAnswer(answer);
            answerResponseDTOList.add(answerResponseDTO);
        }
        return answerResponseDTOList;
    }
}

