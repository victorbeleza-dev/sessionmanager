package com.sessionmanager.repository;

import com.sessionmanager.model.Pauta;
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
public class PautaRepositoryTest {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    protected TestEntityManager entityManager;

    @Test
    void findByIdAndSessionOpen(){
        Pauta pautaInsert = entityManager.persist(Pauta.builder()
                .pautaTitle("teste")
                .pautaDescriptin("teste")
                .dtIns(new Date()).build());

        Pauta pautaResponse = pautaRepository.findPautaByPautaTitle("teste");

        Assertions.assertEquals(pautaInsert, pautaResponse);

        Assertions.assertEquals(pautaInsert.getPautaDescriptin(), pautaResponse.getPautaDescriptin());
        Assertions.assertEquals(pautaInsert.getPautaTitle(), pautaResponse.getPautaTitle());
        Assertions.assertEquals(pautaInsert.getDtIns(), pautaResponse.getDtIns());
        Assertions.assertEquals(pautaInsert.getId(), pautaResponse.getId());
    }

}
