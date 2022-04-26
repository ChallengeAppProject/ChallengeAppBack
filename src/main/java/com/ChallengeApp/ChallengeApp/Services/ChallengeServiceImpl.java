package com.ChallengeApp.ChallengeApp.Services;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Repositories.ChallengeAppRepository;
import com.ChallengeApp.ChallengeApp.dtos.ChallengeRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.ChallengeResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private ChallengeAppRepository challengeAppRepository;
    public ChallengeServiceImpl(ChallengeAppRepository challengeAppRepository) {
        this.challengeAppRepository = challengeAppRepository;
    }
    @Override
    public ChallengeResponseDTO createChallenge(ChallengeRequestDTO challengeRequestDTO) {
        //Transformar el challengeRequestDTO en Challenge
        var challenge = new Challenge();
        challenge.setName(challengeRequestDTO.name);
        //guardarmos el challenge
        challengeAppRepository.save(challenge);
        //Transformar el challenge en challengeResponseDTO
        var challengeResponse = new ChallengeResponseDTO().mapFromChallenge(challenge);
        return challengeResponse;
    }



    @Override
    public ChallengeResponseDTO get(Long id){
        Challenge challenge = challengeAppRepository.findById(id).get();
        var challengeResponse = new ChallengeResponseDTO().mapFromChallenge(challenge);

        return challengeResponse;
    }

    @Override
    public List<ChallengeResponseDTO> getAllChallenges(){
         List<Challenge> challenges= challengeAppRepository.findAll();
         List<ChallengeResponseDTO> challengesDTO = new ArrayList<ChallengeResponseDTO>();

         for(Challenge challenge : challenges){
             ChallengeResponseDTO challengeResponseDTO = new ChallengeResponseDTO();
             challengeResponseDTO.setName(challenge.getName());
             challengeResponseDTO.setId(challenge.getId());
             challengesDTO.add(challengeResponseDTO);
         }
         return challengesDTO;
    }

    @Override
    public ChallengeResponseDTO saveChallenge(ChallengeRequestDTO challengeRequestDTO, Long id) {
        //Buscamos el challenge a actualizar por su id
        Challenge challenge = challengeAppRepository.findById(id).get();
        //Le pasamos el nombre del request al challenge
        challenge.setName(challengeRequestDTO.name);
        //guardamos el challenge
        challengeAppRepository.save(challenge);
        //Transformamos el challenge en un ResponseDTO utilizando el método que incluye la clase ChallengeResponseDTO
        var challengeResponse = new ChallengeResponseDTO().mapFromChallenge(challenge);
        return challengeResponse;
    }

    @Override
    public void delete (Long id){
        Challenge challenge = challengeAppRepository.findById(id).get();
        ChallengeResponseDTO challengeResponseDTO = new ChallengeResponseDTO();
        challengeResponseDTO.setId(challenge.getId());
        challengeAppRepository.deleteById(challengeResponseDTO.getId());
    }

    @Override
    public Challenge getById(Long id) {
        return challengeAppRepository.findById(id).get();
    }
}
