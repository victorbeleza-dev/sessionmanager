package com.sessionmanager.mapper;

import com.sessionmanager.model.Vote;
import com.sessionmanager.model.VotingSession;
import com.sessionmanager.model.dto.VotingSessionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class VotingSessionMapperTest {

    @MockBean
    private VotingSessionMapper votingSessionMapper;

    @Test
    void convertToEntity(){
        VotingSessionDTO votingSessionDTO = VotingSessionDTO.builder()
                .minDurationSession(2)
                .idPauta(2L).build();

        VotingSession votingSession = VotingSession.builder().minDurationSession(2)
                .idPauta(2L).build();

        Mockito.when(votingSessionMapper.convertToEntity(votingSessionDTO)).thenReturn(votingSession);
        VotingSession votingSessionConverted = votingSessionMapper.convertToEntity(votingSessionDTO);

        Assertions.assertEquals(votingSessionConverted, votingSessionConverted);

        Assertions.assertEquals(votingSession.getIdPauta(), votingSessionConverted.getIdPauta());
        Assertions.assertEquals(votingSessionConverted.getMinDurationSession(), votingSessionConverted.getMinDurationSession());
    }
}
