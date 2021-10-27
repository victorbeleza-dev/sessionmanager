package com.sessionmanager.mapper;

import com.sessionmanager.model.Vote;
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
public class VoteMapperTest {

    @MockBean
    private VoteMapper voteMapper;

    @Test
    void convertToEntity(){
        VoteDTO voteDTO = VoteDTO.builder().CPF("019412")
                .vote("SIM")
                .idSession(1L)
                .idAssociated(1L).build();

        Vote vote = Vote.builder().CPF("019412")
                .vote("SIM")
                .idSession(1L)
                .idAssociated(1L).build();

        Mockito.when(voteMapper.convertToEntity(voteDTO)).thenReturn(vote);
        Vote voteConverted = voteMapper.convertToEntity(voteDTO);

        Assertions.assertEquals(vote, voteConverted);

        Assertions.assertEquals(vote.getVote(), voteConverted.getVote());
        Assertions.assertEquals(vote.getDtVote(), voteConverted.getDtVote());
        Assertions.assertEquals(vote.getIdAssociated(), voteConverted.getIdAssociated());
        Assertions.assertEquals(vote.getCPF(), voteConverted.getCPF());
    }
}
