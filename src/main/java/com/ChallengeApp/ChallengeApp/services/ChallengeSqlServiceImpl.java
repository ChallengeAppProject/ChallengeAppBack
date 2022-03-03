package com.ChallengeApp.ChallengeApp.services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Repositories.ChallengeAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeSqlServiceImpl implements ChallengeService {

    @Autowired
    private ChallengeAppRepository challengeAppRepository;

    @Override
    public Challenge get(Long id){
        return challengeAppRepository.findById(id).get();
    }

}
