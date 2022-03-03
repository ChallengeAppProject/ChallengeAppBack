package com.ChallengeApp.ChallengeApp.Models;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imgUrl;
    private String challengeQuestion;
    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;



    public Question(Long id, String imgUrl, String challengeQuestion, Challenge challenge ) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.challengeQuestion = challengeQuestion;
        this.challenge = challenge;
    }

    public Question() {
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

    public Challenge getChallenge() {return challenge;}
}
