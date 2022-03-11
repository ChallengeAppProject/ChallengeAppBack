package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;

import java.util.List;


public interface ChallengeService {


    public Challenge get(Long id);

    List<Challenge> getAllChallenges();


    public Challenge saveChallenge(Challenge challenge);


    public void delete (Long id);

    public Challenge save (Challenge challenge);


}
