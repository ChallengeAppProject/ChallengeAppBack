package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Repositories.ChallengeAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeSqlServiceImpl implements ChallengeService {

    @Autowired
    private ChallengeAppRepository challengeAppRepository;

    @Override
    public Challenge get(Long id){
        return challengeAppRepository.findById(id).get();
    }

    @Override
    public List<Challenge> getAllChallenges(){
        return challengeAppRepository.findAll();
    }

    @Override
    public Challenge saveChallenge(Challenge challenge) {
        return challengeAppRepository.save(challenge);
    }
    @Override
    public void delete (Long id){challengeAppRepository.deleteById(id);
    }

    @Override
    public Challenge save(Challenge challenge) {
        return challengeAppRepository.save(challenge);
    }

}
