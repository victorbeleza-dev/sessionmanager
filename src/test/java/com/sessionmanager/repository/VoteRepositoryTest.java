package com.sessionmanager.repository;

import com.sessionmanager.model.Vote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class VoteRepositoryTest {

    @Autowired
    protected VoteRepository voteRepository;

    @Autowired
    protected TestEntityManager entityManager;

    @Test
    void findByIdAssociatedAndIdSession(){
        Vote voteInsert = entityManager.persist(Vote.builder()
                .idSession(2L)
                .idAssociated(1L)
                .dtVote(new Date())
                .vote("SIM")
                .CPF("27881442026").build());

        Vote voteResponse = voteRepository.findByIdAssociatedAndIdSession(1L, 2L);

        Assertions.assertEquals(voteInsert, voteResponse);

        Assertions.assertEquals(voteInsert.getId(), voteResponse.getId());
        Assertions.assertEquals(voteInsert.getDtVote(), voteResponse.getDtVote());
        Assertions.assertEquals(voteInsert.getVote(), voteResponse.getVote());
        Assertions.assertEquals(voteInsert.getIdSession(), voteResponse.getIdSession());
        Assertions.assertEquals(voteInsert.getCPF(), voteResponse.getCPF());
        Assertions.assertEquals(voteInsert.getIdAssociated(), voteResponse.getIdAssociated());
    }

    @Test
    void findAllByIdSession(){
        Vote voteInsert1 = entityManager.persist(Vote.builder()
                .idSession(2L)
                .idAssociated(1L)
                .dtVote(new Date())
                .vote("SIM")
                .CPF("1234567").build());

        Vote voteInsert2 = entityManager.persist(Vote.builder()
                .idSession(2L)
                .idAssociated(2L)
                .dtVote(new Date())
                .vote("SIM")
                .CPF("12378").build());

        List<Vote> listVote = voteRepository.findAllByIdSession(2L);

        Assertions.assertEquals(2, listVote.size());

        Vote voteFound1 = listVote.stream().filter((vote -> vote.equals(voteInsert1))).findFirst().orElse(new Vote());
        Vote voteFound2 = listVote.stream().filter((vote -> vote.equals(voteInsert2))).findFirst().orElse(new Vote());

        Assertions.assertEquals(voteInsert1.getId(), voteFound1.getId());
        Assertions.assertEquals(voteInsert1.getDtVote(), voteFound1.getDtVote());
        Assertions.assertEquals(voteInsert1.getVote(), voteFound1.getVote());
        Assertions.assertEquals(voteInsert1.getIdSession(), voteFound1.getIdSession());
        Assertions.assertEquals(voteInsert1.getCPF(), voteFound1.getCPF());
        Assertions.assertEquals(voteInsert1.getIdAssociated(), voteFound1.getIdAssociated());

        Assertions.assertEquals(voteInsert2.getId(), voteFound2.getId());
        Assertions.assertEquals(voteInsert2.getDtVote(), voteFound2.getDtVote());
        Assertions.assertEquals(voteInsert2.getVote(), voteFound2.getVote());
        Assertions.assertEquals(voteInsert2.getIdSession(), voteFound2.getIdSession());
        Assertions.assertEquals(voteInsert2.getCPF(), voteFound2.getCPF());
        Assertions.assertEquals(voteInsert2.getIdAssociated(), voteFound2.getIdAssociated());
    }

}
