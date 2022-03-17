package com.ChallengeApp.ChallengeApp.dtos;

import com.ChallengeApp.ChallengeApp.Models.User;
import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuestionResponseDTO {
    public Long userId;
    public Long questionId;
    public Long challengeAnswerId;
    public Long challengeId;
    public Boolean isCorrect;
    public User user;

    public UserQuestionResponseDTO mapFromUserQuestion(UserQuestion userQuestion){

        this.userId = userQuestion.getUser().getId();
        this.questionId = userQuestion.getQuestion().getId();
        this.challengeAnswerId = userQuestion.getChallengeAnswer().getId();
        this.challengeId = userQuestion.challenge().getId();
        this.isCorrect = userQuestion.isUserRight();
        this.user = new User(userQuestion.getUser().getId(), userQuestion.getUser().getUserName());

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


}
