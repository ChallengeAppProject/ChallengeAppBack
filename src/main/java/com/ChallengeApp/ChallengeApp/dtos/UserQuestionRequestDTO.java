package com.ChallengeApp.ChallengeApp.dtos;

import com.ChallengeApp.ChallengeApp.Models.ChallengeAnswer;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Models.User;
import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuestionRequestDTO{

        public Long userId;
        public Long questionId;
        public Long challengeAnswerId;
        public Long challengeId;

    }


