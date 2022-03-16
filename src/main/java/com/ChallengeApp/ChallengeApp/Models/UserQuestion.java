package com.ChallengeApp.ChallengeApp.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Question question;

    @ManyToOne
    private ChallengeAnswer challengeAnswer;

    public Boolean isUserRight() {
        return challengeAnswer.isCorrect();
    }

    @JsonProperty
    public Challenge challenge() {
        return this.question.getChallenge();
    }







}
