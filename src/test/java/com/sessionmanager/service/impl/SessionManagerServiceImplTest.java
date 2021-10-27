package com.sessionmanager.service.impl;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.model.VotingSession;
import com.sessionmanager.repository.PautaRepository;
import com.sessionmanager.repository.SessionManagerRepository;
import com.sessionmanager.repository.VoteRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureMockMvc
public class SessionManagerServiceImplTest {

    @Mock
    private SessionManagerRepository repository;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private SessionManagerServiceImpl sessionManagerService;

    @Autowired
    protected TestEntityManager entityManager;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        standaloneSetup(this.sessionManagerService);

        Pauta pautaInsert = entityManager.persist(Pauta.builder()
                .pautaTitle("teste")
                .pautaDescriptin("teste")
                .dtIns(new Date()).build());

        Mockito.when(pautaRepository.findById(1L)).thenReturn(Optional.ofNullable(pautaInsert));
    }

    @Test
    void returnSuccess_whenRegisterVotingSession() throws NotFoundException {

        VotingSession votingSessionInsert = VotingSession.builder()
                .sessionOpen(true)
                .idPauta(1L)
                .dtOpenSession(new Date())
                .votesYes(0)
                .votesNo(0)
                .minDurationSession(1)
                .build();

        Mockito.when(sessionManagerService.registerVotingSession(votingSessionInsert)).thenReturn(votingSessionInsert);

        VotingSession votingSessionResponse = sessionManagerService.registerVotingSession(votingSessionInsert);

        assertThat(votingSessionInsert).isEqualTo(votingSessionResponse);
    }

    @Test
    void returnErro_whenPautaNotFoundRegisterVotingSession() throws NotFoundException {

        VotingSession votingSessionInsert = VotingSession.builder()
                .sessionOpen(true)
                .idPauta(5L)
                .dtOpenSession(new Date())
                .votesYes(0)
                .votesNo(0)
                .minDurationSession(1)
                .build();

        assertThrows(NotFoundException.class, () -> sessionManagerService.registerVotingSession(votingSessionInsert));
    }

    @Test
    void endVotingSession() throws NotFoundException {

        VotingSession votingSessionInsert = VotingSession.builder()
                .sessionOpen(true)
                .idPauta(1L)
                .dtOpenSession(new Date())
                .votesYes(0)
                .votesNo(0)
                .minDurationSession(1)
                .build();

        Mockito.when(sessionManagerService.endVotingSession(1L)).thenReturn(votingSessionInsert);
        Mockito.when(sessionManagerService.endVotingSession(1L)).thenReturn(votingSessionInsert);

        VotingSession votingSessionResponse = sessionManagerService.registerVotingSession(votingSessionInsert);

        assertThat(votingSessionInsert).isEqualTo(votingSessionResponse);
    }

}
