package com.ChallengeApp.ChallengeApp.dtos;

import com.ChallengeApp.ChallengeApp.Models.Question;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionResponseDTO {
    public Long id;
    public Long challengeId;
    public String imgUrl;
    public String challengeQuestion;

    public QuestionResponseDTO mapFromQuestion(Question question){
        this.id = question.getId();
        this.challengeId = question.getChallenge().getId();
        this.imgUrl = question.getImgUrl();
        this.challengeQuestion = question.getChallengeQuestion();

        return this;
    }
}
