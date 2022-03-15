package com.ChallengeApp.ChallengeApp.Repositories;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.User;
import com.ChallengeApp.ChallengeApp.Models.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuestionRepository  extends JpaRepository<UserQuestion, Long> {
    List<UserQuestion> findAllByUserAndQuestion_Challenge(User user, Challenge challenge);

}
