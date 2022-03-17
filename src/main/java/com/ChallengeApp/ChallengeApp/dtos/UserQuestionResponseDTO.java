package com.ChallengeApp.ChallengeApp.dtos;

import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuestionResponseDTO {

    public User user;
    public Challenge challenge;
    public Question question;
    public ChallengeAnswer challengeAnswer;

    public UserQuestionResponseDTO mapFromUserQuestion(UserQuestion userQuestion){

        this.user = new User(userQuestion.getUser().getId(), userQuestion.getUser().getUserName());
        this.challenge = new Challenge(userQuestion.challenge().getId(),userQuestion.challenge().getName());
        this.question = new Question(userQuestion.getQuestion().getId(), userQuestion.getQuestion().getChallengeQuestion());
        this.challengeAnswer = new ChallengeAnswer(userQuestion.getChallengeAnswer().getId(), userQuestion.getChallengeAnswer()
                .getTextAnswer(), userQuestion.getChallengeAnswer().isCorrect());

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
        public  String challengeName;

        public Challenge(Long id, String name) {
            this.id = id;
            this.challengeName = name;
        }
    }

    class Question {
        public Long id;
        public String challenQuestion;


        public Question(Long id, String challengeQuestion) {
            this.id = id;
            this.challenQuestion = challengeQuestion;

        }
    }

    class ChallengeAnswer {
        public Long id;
        public String answer;
        public Boolean isCorrect;

        public ChallengeAnswer(Long id, String textAnswer, boolean correct) {
            this.id = id;
            this.answer = textAnswer;
            this.isCorrect = correct;
        }
    }


}
