package com.ChallengeApp.ChallengeApp.dtos;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChallengeResponseDTO {
    public Long id;
    public String name;

    public ChallengeResponseDTO mapFromChallenge(Challenge challenge){
        this.id= challenge.getId();
        this.name = challenge.getName();
        return this;
    }

}
