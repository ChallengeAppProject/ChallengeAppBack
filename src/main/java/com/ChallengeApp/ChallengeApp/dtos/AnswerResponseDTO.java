package com.ChallengeApp.ChallengeApp.dtos;

import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AnswerResponseDTO {
    private Long id;
    private Long questionId;
    private String textAnswer;
    private Boolean correctAnswer;

    public AnswerResponseDTO mapFromAnswer(ChallengeAnswer answer){
        this.id = answer.getId();
        this.questionId = answer.getQuestion().getId();
        this.textAnswer = answer.getTextAnswer();
        this.correctAnswer = answer.getCorrectAnswer();

        return this;
    }
}
