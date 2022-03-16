package com.ChallengeApp.ChallengeApp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionRequestDTO {
    public Long challengeId;
    public String imgUrl;
    public String challengeQuestion;
}
