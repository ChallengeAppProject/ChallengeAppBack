package com.ChallengeApp.ChallengeApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserQuestionRequestDTO{

        public Long userId;
        public Long questionId;
        public Long challengeAnswerId;
        public Long challengeId;

    }


