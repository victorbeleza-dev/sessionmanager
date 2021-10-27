package com.sessionmanager.mapper;

import com.sessionmanager.model.Vote;
import com.sessionmanager.model.dto.VoteDTO;

public class VoteMapper implements GenericMapper<Vote, VoteDTO>{

    @Override
    public Vote convertToEntity(VoteDTO vote) {
        return Vote.builder().vote(vote.getVote())
                .idSession(vote.getIdSession())
                .idAssociated(vote.getIdAssociated()).build();
    }

}
