package com.ChallengeApp.ChallengeApp.auth.facade;


import com.ChallengeApp.ChallengeApp.Models.User;

import com.ChallengeApp.ChallengeApp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {
    @Autowired
    UserRepository userRepository;

    public User getAuthUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (!optionalUser.isPresent()) {
            return null;
        }

        return optionalUser.get();
    }
}
