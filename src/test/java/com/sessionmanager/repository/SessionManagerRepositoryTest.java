package com.sessionmanager.repository;

import com.sessionmanager.model.VotingSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SessionManagerRepositoryTest {

    @Autowired
    private SessionManagerRepository sessionManagerRepository;

    @Autowired
    protected TestEntityManager entityManager;

    @Test
    void findByIdAndSessionOpen(){
        VotingSession votingSession = entityManager.persist(VotingSession.builder()
                        .sessionOpen(true)
                        .idPauta(1L)
                        .dtOpenSession(new Date())
                        .votesYes(0)
                        .votesNo(0)
                        .minDurationSession(1)
                .build());

        VotingSession votingResponse = sessionManagerRepository.findByIdAndSessionOpen(1L, true);

        Assertions.assertEquals(votingSession, votingResponse);

        Assertions.assertEquals(votingSession.getId(), votingResponse.getId());
        Assertions.assertEquals(votingSession.getDtOpenSession(), votingResponse.getDtOpenSession());
        Assertions.assertEquals(votingSession.isSessionOpen(), votingResponse.isSessionOpen());
        Assertions.assertEquals(votingSession.getMinDurationSession(), votingResponse.getMinDurationSession());
        Assertions.assertEquals(votingSession.getIdPauta(), votingResponse.getIdPauta());
        Assertions.assertEquals(votingSession.getVotesNo(), votingResponse.getVotesNo());
        Assertions.assertEquals(votingSession.getVotesYes(), votingResponse.getVotesYes());
    }
}
