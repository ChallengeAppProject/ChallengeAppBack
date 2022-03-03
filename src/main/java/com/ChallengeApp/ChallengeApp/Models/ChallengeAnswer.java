package com.ChallengeApp.ChallengeApp.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChallengeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private boolean correctAnswer;
    private String textAnswer;
    private Question question;



    public ChallengeAnswer(Long id, boolean correctAnswer, String textAnswer, Question question) {
        this.id = id;
        this.correctAnswer = correctAnswer;
        this.textAnswer = textAnswer;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public boolean isCorrect() {
        return correctAnswer;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public Question getQuestion() {
        return question;
    }
}
