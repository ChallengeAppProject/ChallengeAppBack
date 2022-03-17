package com.ChallengeApp.ChallengeApp.dtos;

import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter
public class QuestionListResponseDTO {
    private User user;
    private Challenge challenge;
    private int correctAnswers;
    private int incorrectAnswers;
    private List<UserQuestion> userQuestionList=new ArrayList<UserQuestion>();

    public QuestionListResponseDTO mapFromQuestionsList(List<UserQuestion> userQuestionList){
        this.user = new User(userQuestionList.get(0).getUser().getId(),userQuestionList.get(0).getUser().getUserName());
        this.challenge = new Challenge(userQuestionList.get(0).challenge().getId(),userQuestionList.get(0).challenge().getName());
        this.correctAnswers =userQuestionList.get(0).correctAnswersCounter(userQuestionList);
        this.incorrectAnswers = userQuestionList.size()-correctAnswers;
        return this;
    }

    class User {
        public Long id;
        public String userName;

        public User(Long id, String userName) {
            this.id = id;
            this.userName = userName;
        }
    }

    class Challenge {
        public Long id;
        public String challengeName;

        public Challenge(Long id, String name) {
            this.id = id;
            this.challengeName = name;
        }
    }

}
