package com.ChallengeApp.ChallengeApp.Repositories;

import com.ChallengeApp.ChallengeApp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
