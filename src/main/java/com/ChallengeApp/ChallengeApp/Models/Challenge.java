package com.ChallengeApp.ChallengeApp.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Challenge {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private Long questionsId;

    public Challenge() {
    }

    public Challenge(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getQuestionsId() {
        return questionsId;
    }
}

