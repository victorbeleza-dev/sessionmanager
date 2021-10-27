package com.sessionmanager.mapper;

import com.sessionmanager.model.Vote;
import com.sessionmanager.model.dto.VoteDTO;

import java.util.Date;

public class VoteMapper implements GenericMapper<Vote, VoteDTO>{

    @Override
    public Vote convertToEntity(VoteDTO vote) {
        return Vote.builder().vote(vote.getVote())
                .dtVote(new Date())
                .idSession(vote.getIdSession())
                .idAssociated(vote.getIdAssociated())
                .CPF(vote.getCPF()).build();
    }

}
