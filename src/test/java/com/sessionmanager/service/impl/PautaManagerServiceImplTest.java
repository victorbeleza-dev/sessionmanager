package com.sessionmanager.service.impl;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureMockMvc
public class PautaManagerServiceImplTest {

    @InjectMocks
    private PautaManagerServiceImpl pautaManagerService;

    @MockBean
    private PautaRepository pautaRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        standaloneSetup(this.pautaManagerService);
    }

    @Test
    void returnSuccess_whenInsertNewPauta() throws Exception {

        Pauta pautaInsert = Pauta.builder()
                .pautaTitle("teste")
                .pautaDescriptin("teste")
                .dtIns(new Date()).build();

        Mockito.when(pautaRepository.save(pautaInsert)).thenReturn(pautaInsert);
        Pauta pautaResponse = pautaManagerService.registerPauta(pautaInsert);
        assertThat(pautaResponse).isEqualTo(pautaInsert);
    }
}
