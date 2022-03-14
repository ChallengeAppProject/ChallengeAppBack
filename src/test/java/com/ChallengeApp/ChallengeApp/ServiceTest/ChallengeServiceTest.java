package com.ChallengeApp.ChallengeApp.ServiceTest;

import com.ChallengeApp.ChallengeApp.Models.Challenge;
import com.ChallengeApp.ChallengeApp.Models.Question;
import com.ChallengeApp.ChallengeApp.Repositories.ChallengeAppRepository;
import com.ChallengeApp.ChallengeApp.Repositories.QuestionRepository;
import com.ChallengeApp.ChallengeApp.Services.ChallengeSqlServiceImpl;
import com.ChallengeApp.ChallengeApp.Services.QuestionSqlServiceImp;
import com.ChallengeApp.ChallengeApp.dtos.ChallengeRequestDTO;
import com.ChallengeApp.ChallengeApp.dtos.ChallengeResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ChallengeServiceTest {

    @Mock
    ChallengeAppRepository challengeAppRepository;
    @Mock
    QuestionRepository questionRepository;
    @Test
    void whenSaveChallengeItShouldReturnChallenge(){
        Challenge challenge = new Challenge(1L,"mockChallenge");
        Mockito.when(challengeAppRepository.save(challenge)).thenReturn(challenge);

        var result = challengeAppRepository.save(challenge);


        assertThat(result.getId(),equalTo(1L));
        assertThat(result.getName(), equalTo("mockChallenge"));
        assertThat(result, equalTo(challenge));
    }

    @Test
    void whenDeletingChallengeItShouldBeDeleted() {
        Challenge testChallenge = new Challenge(2L,"mockChallenge");
        ChallengeResponseDTO challengeResponseDTO = new ChallengeResponseDTO();
        challengeResponseDTO.setId(testChallenge.getId());
        //mediante Mockito pedimos que cuando se use la función .findById del repositorio mockeado
        // nos devuelva el challenge
        Mockito.when(challengeAppRepository.findById(2L)).thenReturn(Optional.of(testChallenge));

        var challService = new ChallengeSqlServiceImpl(challengeAppRepository);

        challService.delete(challengeResponseDTO.getId());

        verify(challengeAppRepository).deleteById(2L);
    }

    @Test
    void whenClientWantAllChallengesServerResponseWithADTOList(){
        //GIVEN - ARRANGE
        //Creamos challenges y los añadimos a una lista
        Challenge challenge1 = new Challenge(1L, "Biología");
        Challenge challenge2 = new Challenge(2L, "Mates");
        List<Challenge> challengeList = new ArrayList<Challenge>();
        challengeList.add(challenge1);
        challengeList.add(challenge2);

        //CreamosUna lista de DTO's
        List<ChallengeResponseDTO> challengeResponseDTOList = new ArrayList<ChallengeResponseDTO>();

        //Mediante un bucle forEach de la lista de challenges añadimos un DTO a la lista de DTO's
        // y seteamos su name geteándolo del challenge
        for(Challenge challenge : challengeList){
            ChallengeResponseDTO challengeResponseDTO = new ChallengeResponseDTO();
            challengeResponseDTO.setName(challenge.getName());
            challengeResponseDTOList.add(challengeResponseDTO);
        }

        //mediante Mockito pedimos que cuando se use la función .findAll del repositorio mockeado
        // nos devuelva la lista de Challenges
        Mockito.when(challengeAppRepository.findAll()).thenReturn(challengeList);

        //Creamos una implementación del servicio
        ChallengeSqlServiceImpl challengeSqlService = new ChallengeSqlServiceImpl(challengeAppRepository);

        //WHEN - ACT
        // Utilizamos la función getAllChallenges del servicio (la que usa la .findAll del repo);

        var sut =challengeSqlService.getAllChallenges();

        //THEN - ASSERT
        assertEquals(challengeResponseDTOList.size(), sut.size() );
        assertThat(sut.get(0).getName(), equalTo("Biología"));
        assertThat(challengeResponseDTOList.get(0).getName(), equalTo("Biología"));
    }

    @Test
    void whenClientWantOneChallengesServerResponseWithADTO(){
        //GIVEN -ARRANGE creamos un challenge y un DTO
        Challenge challenge = new Challenge(1L, "Mates");
        ChallengeResponseDTO challengeResponseDTO = new ChallengeResponseDTO();

        //Le pasamos el name del challenge al DTO
        challengeResponseDTO.setName(challenge.getName());

        //mediante Mockito pedimos que cuando se use la función .findById del repositorio mockeado
        // nos devuelva el challenge
        Mockito.when(challengeAppRepository.findById(1L)).thenReturn(Optional.of(challenge));

        //Creamos una implementación del servicio
        ChallengeSqlServiceImpl challengeSqlService = new ChallengeSqlServiceImpl(challengeAppRepository);

        //WHEN - ACT
        // Utilizamos la función get del servicio (la que usa la .findById del repo);
        var sut =challengeSqlService.get(1L);

        assertEquals(sut.getName(),challengeResponseDTO.getName());
    }

    @Test
    void clientCanSaveAChallengeUsingARequestDTO(){
        //GIVEN -ARRANGE creamos un DTORequest y le añadimos nombre
        ChallengeRequestDTO challengeRequestDTO = new ChallengeRequestDTO();
        challengeRequestDTO.setName("Sociales");
        //creamos un challenge y le añadimos el nombre del DTORequest
        Challenge challenge = new Challenge();
        challenge.setName(challengeRequestDTO.getName());

        //mediante Mockito pedimos que cuando se use la función .save del repositorio mockeado
        // nos devuelva el challenge
        Mockito.when(challengeAppRepository.save(challenge)).thenReturn(challenge);

        //Creamos una implementación del servicio
        ChallengeSqlServiceImpl challengeSqlService = new ChallengeSqlServiceImpl(challengeAppRepository);

        //WHEN -ACT
        // Utilizamos la función createChallenge del servicio (la que usa la .save del repo);
        var sut = challengeSqlService.createChallenge(challengeRequestDTO);

        //THEN - ASSERT

        assertEquals(challengeRequestDTO.getName(), sut.getName());
    }

    @Test
    void whenClientWantDeleteAChallengeAResponseDTOCanDoIt(){
        //GIVEN -ARRANGE creamos un DTOResponse y un challenge
        ChallengeResponseDTO challengeResponseDTO = new ChallengeResponseDTO();
        Challenge challenge = new Challenge(1L, "Ingeniería aereoespacial");
        //Añadimos la id del challenge al DTO
        challengeResponseDTO.setId(challenge.getId());
        //mediante Mockito pedimos que cuando se use la función .findById del repositorio mockeado
        // nos devuelva el challenge
        Mockito.when(challengeAppRepository.findById(1L)).thenReturn(Optional.of(challenge));

        //Creamos una implementación del servicio
        var challengeSqlService = new ChallengeSqlServiceImpl(challengeAppRepository);

        //WHEN -ACT
        // Utilizamos la función delete del servicio (la que usa la .deleteById del repo);
        challengeSqlService.delete(challengeResponseDTO.getId());

        //THEN - ASSERT
        assertEquals(1L, challengeResponseDTO.getId());
        verify(challengeAppRepository).deleteById(1L);

    }

    @Test
    void clientCanUpdateAChallengeUsingADTORequest(){
        //GIVEN -ARRANGE creamos un DTORequest y le añadimos nombre
        ChallengeRequestDTO challengeRequestDTO = new ChallengeRequestDTO();
        challengeRequestDTO.setName("Sociales");
        //creamos un challenge y le añadimos el nombre del DTORequest y un id
        Challenge challenge = new Challenge();
        //cambiamos el name
        challengeRequestDTO.setName("Naturales");
        challenge.setName(challengeRequestDTO.getName());
        challenge.setId(1L);
        //Transformamos el challenge en ResponseDTO
        var challengeResponse = new ChallengeResponseDTO().mapFromChallenge(challenge);
        //mediante Mockito pedimos que cuando se use la función .findById del repositorio mockeado
        // nos devuelva el challenge
        Mockito.when(challengeAppRepository.findById(1L)).thenReturn(Optional.of(challenge));
        //mediante Mockito pedimos que cuando se use la función .save del repositorio mockeado
        // nos devuelva el challenge
        Mockito.when(challengeAppRepository.save(challenge)).thenReturn(challenge);
        //Creamos una implementación del servicio
        ChallengeSqlServiceImpl challengeSqlService = new ChallengeSqlServiceImpl(challengeAppRepository);

        //WHEN -ACT
        // Utilizamos la función saveChallenge del servicio (la que usa la .findById y .save del repo);
        var sut = challengeSqlService.saveChallenge(challengeRequestDTO, challengeResponse.getId());

        //THEN - ASSERT

        assertEquals("Naturales", sut.getName());

    }
}
