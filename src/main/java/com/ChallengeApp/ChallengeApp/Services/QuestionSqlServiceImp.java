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
    public QuestionResponseDTO get(Long id)  {
        Question question = questionRepository.findById(id).get();
        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        var questionResponse  = new QuestionResponseDTO().mapFromQuestion(question);
        return questionResponse;}

    @Override
    public List<QuestionResponseDTO> getAllQuestion(){
        List<Question> questions = questionRepository.findAll();
        List<QuestionResponseDTO> questionsDTO = new ArrayList<QuestionResponseDTO>();

        for (Question question : questions) {
            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
            questionResponseDTO.setChallengeQuestion(question.getChallengeQuestion());
            questionsDTO.add(questionResponseDTO);

        }
    }

    @Override
    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }
    //Faltaría hacer el QuestionRequestDTO que no tengo claro
    //POr qué hay dos save para la question? (linea 56)

    @Override
    public void delete (Long id){
        Question question = questionRepository.findById(id).get();
        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        questionResponseDTO.setId(question.getId());
        questionRepository.deleteById(questionResponseDTO.getId());
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
