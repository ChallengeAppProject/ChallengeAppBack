package com.ChallengeApp.ChallengeApp.Models;

public class Question {
    private Long id;
    private String imgUrl;
    private String challengeQuestion;

    public Question(Long id, String imgUrl,String challengeQuestion) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.challengeQuestion = challengeQuestion;
    }

    public Long getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getChallengeQuestion() {
        return challengeQuestion;
    }
}
