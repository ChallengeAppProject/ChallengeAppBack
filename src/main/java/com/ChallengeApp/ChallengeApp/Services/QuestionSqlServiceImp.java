package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.dtos.QuestionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Long;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionSqlServiceImp implements QuestionService {

    public QuestionSqlServiceImp(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

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

    @Override
    public void delete (Long id){questionRepository.deleteById(id);
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<QuestionResponseDTO> getAllByChallenge(Challenge challenge) {

        List<Question> questionList = questionRepository.findAllByChallenge(challenge);

        List<QuestionResponseDTO> questionResponseDTOList = new ArrayList<QuestionResponseDTO>();

        for(Question question :questionList){
            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
            questionResponseDTO.mapFromQuestion(question);
            questionResponseDTOList.add(questionResponseDTO);
        }
        return questionResponseDTOList;
    }
}
