package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.AnswerRepository;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.dtos.AnswerRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.AnswerResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class AnswerSqlServiceImpl implements AnswerService{

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    public AnswerSqlServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<ChallengeAnswer> getAllAnswer(){
        return answerRepository.findAll();
    }

    @Override
    public AnswerResponseDTO saveAnswer(AnswerRequestDTO answerRequestDTO){
        //Transformar el answerRequestDTO en ChallengeAnswer
        var answer = new ChallengeAnswer();
        answer.setTextAnswer(answerRequestDTO.getTextAnswer());
        answer.setQuestion(questionRepository.findById(answerRequestDTO.getQuestionId()).get());
        answer.setCorrectAnswer(answerRequestDTO.getCorrectAnswer());
        //guardarmos la answer
        answerRepository.save(answer);

        AnswerResponseDTO answerResponseDTO =new AnswerResponseDTO().mapFromAnswer(answer);
        return answerResponseDTO;
    }

    @Override
    public AnswerResponseDTO getAnswerById(Long id)  {
        ChallengeAnswer answer = answerRepository.findById(id).get();
        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO().mapFromAnswer(answer);

        return answerResponseDTO;}

    @Override
    public void delete (Long id){answerRepository.deleteById(id);
    }
    @Override
    public AnswerResponseDTO createAnswer(AnswerRequestDTO answerRequestDTO) {
        //Transformar el answerRequestDTO en ChallengeAnswer
        var answer = new ChallengeAnswer();
        answer.setTextAnswer(answerRequestDTO.getTextAnswer());
        answer.setQuestion(questionRepository.findById(answerRequestDTO.getQuestionId()).get());
        answer.setCorrectAnswer(answerRequestDTO.getCorrectAnswer());
        //guardarmos la answer
        answerRepository.save(answer);
        //Transformar answer en answerResponseDTO
        var answerResponse = new AnswerResponseDTO().mapFromAnswer(answer);
        return answerResponse;
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

