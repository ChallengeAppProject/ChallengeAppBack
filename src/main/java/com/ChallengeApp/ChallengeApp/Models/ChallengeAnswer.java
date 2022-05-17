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

@Table(name = "challenge_answer")
public class ChallengeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean correctAnswer;
    private String textAnswer;
    @ManyToOne //(cascade=CascadeType.REMOVE) Hay algo parecido pero que fuera al revés¿?
    // es decir, cuando elimino un challenge, automáticamente se eliminen sus preguntas y respuestas asociadas?
    @JoinColumn(name = "question_id")
    private Question question;


    public boolean isCorrect() {
        return correctAnswer;
    }

}

