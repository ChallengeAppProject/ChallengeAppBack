package com.ChallengeApp.ChallengeApp.dtos;

import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuestionResponseDTO {
    public Long userId;
    public Long questionId;
    public Long challengeAnswerId;
    public Long challengeId;


    public UserQuestionResponseDTO mapFromUserQuestion(UserQuestion userQuestion){

        this.userId = userQuestion.getId();
        this.questionId = userQuestion.getQuestion().getId();
        this.challengeAnswerId = userQuestion.getChallengeAnswer().getId();
        this.challengeId = userQuestion.challenge().getId();

        return this;
    }
}
