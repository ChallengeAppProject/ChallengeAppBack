package com.ChallengeApp.ChallengeApp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AnswerRequestDTO {
    public Long answerId;
    public Long questionId;
    public String textAnswer;
    public Boolean correctAnswer;
}
