package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;

import java.util.List;


public interface ChallengeService {


    public Challenge get(Long id);

    List<Challenge> getAllChallenges();


    public Challenge saveChallenge(Challenge challenge);


}
