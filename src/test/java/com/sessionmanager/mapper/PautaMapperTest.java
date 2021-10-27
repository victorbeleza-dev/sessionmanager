package com.sessionmanager.mapper;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.model.dto.PautaDTO;
import com.sessionmanager.model.dto.VoteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PautaMapperTest {

    @MockBean
    private PautaMapper pautaMapper;

    @Test
    void convertToEntity(){
        PautaDTO pautaDTO = PautaDTO.builder()
                .pautaDescriptin("teste")
                .pautaTitle("teste")
                .build();

        Pauta pauta = Pauta.builder()
                .pautaDescriptin("teste")
                .pautaTitle("teste").build();

        Mockito.when(pautaMapper.convertToEntity(pautaDTO)).thenReturn(pauta);
        Pauta pautaConverted = pautaMapper.convertToEntity(pautaDTO);

        Assertions.assertEquals(pauta, pautaConverted);

        Assertions.assertEquals(pauta.getPautaTitle(), pautaConverted.getPautaTitle());
        Assertions.assertEquals(pauta.getPautaDescriptin(), pautaConverted.getPautaDescriptin());
    }
}
