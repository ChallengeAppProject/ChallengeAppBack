package com.ChallengeApp.ChallengeApp.Repositories;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ChallengeAppRepository extends JpaRepository <Challenge, Long>{

}
