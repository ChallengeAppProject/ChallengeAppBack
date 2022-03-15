package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.dtos.ChallengeRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.ChallengeResponseDTO;

import java.util.List;


public interface ChallengeService {

    public ChallengeResponseDTO createChallenge(ChallengeRequestDTO challengeRequestDTO);

    public ChallengeResponseDTO saveChallenge(ChallengeRequestDTO challengeRequestDTO, Long id);

    public ChallengeResponseDTO get(Long id);

    public List<ChallengeResponseDTO> getAllChallenges();

    public void delete (Long id);

    public Challenge getById(Long id);






}
