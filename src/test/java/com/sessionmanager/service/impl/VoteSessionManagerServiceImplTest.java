package com.sessionmanager.service.impl;

import com.sessionmanager.client.IsAbleToVoteClient;
import com.sessionmanager.model.AbleToVote;
import com.sessionmanager.model.Pauta;
import com.sessionmanager.model.Vote;
import com.sessionmanager.model.VotingSession;
import com.sessionmanager.repository.PautaRepository;
import com.sessionmanager.repository.SessionManagerRepository;
import com.sessionmanager.repository.VoteRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
public class VoteSessionManagerServiceImplTest {

    @InjectMocks
    private VoteSessionManagerServiceImpl voteSessionManagerService;

    @MockBean
    private VoteRepository voteRepository;

    @InjectMocks
    private SessionManagerServiceImpl sessionManagerService;

    @MockBean
    private SessionManagerRepository sessionManagerRepository;

    @MockBean
    private PautaRepository pautaRepository;

    @MockBean
    private IsAbleToVoteClient isAbleToVoteClient;

    @Autowired
    protected TestEntityManager entityManager;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        standaloneSetup(this.voteSessionManagerService);

        Pauta pautaInsert = entityManager.persist(Pauta.builder()
                .pautaTitle("teste")
                .pautaDescriptin("teste")
                .dtIns(new Date()).build());

        VotingSession votingSession = entityManager.persist(VotingSession.builder()
                .sessionOpen(true)
                .idPauta(1L)
                .dtOpenSession(new Date())
                .votesYes(0)
                .votesNo(0)
                .minDurationSession(1)
                .build());
    }

    @Test
    void returnError_whenCpfInvalidregisterVote() throws Exception {
        Vote vote = Vote.builder()
                .idSession(1L)
                .idAssociated(1L)
                .dtVote(new Date())
                .vote("SIM")
                .CPF("02576552200").build();

        VotingSession votingSession = VotingSession.builder()
                .sessionOpen(true)
                .idPauta(1L)
                .dtOpenSession(new Date())
                .votesYes(0)
                .votesNo(0)
                .minDurationSession(1)
                .build();
        assertThrows(Exception.class, () -> sessionManagerService.verifySessionExist(1L));
        assertThrows(Exception.class, () -> voteSessionManagerService.registerVote(vote));
    }
}
