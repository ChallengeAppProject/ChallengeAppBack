package com.ChallengeApp.ChallengeApp.Models;

public class Question {
    private Long id;
    private String imgUrl;
    private String challengeQuestion;
    private Long challengeId;



    public Question(Long id, String imgUrl, String challengeQuestion, Long challengeId ) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.challengeQuestion = challengeQuestion;
        this.challengeId = challengeId;
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

    public Long getChallengeId() {return challengeId;}
}
