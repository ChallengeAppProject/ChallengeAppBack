package com.ChallengeApp.ChallengeApp.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_question")
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
    public Challenge challenge() {return this.question.getChallenge();}

    public int correctAnswersCounter(List <UserQuestion> userQuestionList){
        var counter = 0;
        for(UserQuestion userQuestion: userQuestionList){
            if(userQuestion.isUserRight()){
                counter+=1;
            }
        }
        return counter;

    }

}
