package com.ChallengeApp.ChallengeApp.Repositories;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ChallengeAppRepository extends JpaRepository <Challenge, Long>{
    //@Query (value = "SELECT * FROM question WHERE challenge.id = challenge_id")

   // List<Question> findAllQuestionsByChallengeId();
}
