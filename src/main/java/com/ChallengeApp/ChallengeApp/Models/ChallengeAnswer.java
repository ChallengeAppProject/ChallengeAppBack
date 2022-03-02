package com.ChallengeApp.ChallengeApp.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ChallengeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private boolean correctAnswer;
    private String textAnswer;
    private Long questionId;



    public ChallengeAnswer(Long id, boolean correctAnswer, String textAnswer, Long questionId) {
        this.id = id;
        this.correctAnswer = correctAnswer;
        this.textAnswer = textAnswer;
        this.questionId = questionId;
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

    public Long getQuestionId() {
        return questionId;
    }
}
