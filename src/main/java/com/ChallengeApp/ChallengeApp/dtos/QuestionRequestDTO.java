package com.ChallengeApp.ChallengeApp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionRequestDTO {
    private Long challengeId;
    private String imgUrl;
    private String challengeQuestion;
}
