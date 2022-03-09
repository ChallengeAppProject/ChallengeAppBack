package com.ChallengeApp.ChallengeApp.Models;

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


public class ChallengeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private boolean correctAnswer;
    private String textAnswer;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


    public boolean isCorrect() {
        return correctAnswer;
    }

}

