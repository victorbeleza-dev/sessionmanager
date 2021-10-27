package com.sessionmanager.mapper;

import com.sessionmanager.model.VotingSession;
import com.sessionmanager.model.dto.VotingSessionDTO;

import java.util.Date;

public class VotingSessionMapper implements GenericMapper<VotingSession, VotingSessionDTO>{

    @Override
    public VotingSession convertToEntity(VotingSessionDTO dto) {
        return VotingSession.builder().dtOpenSession(new Date())
                .idPauta(dto.getIdPauta())
                .minDurationSession(verifyMinSesion(dto))
                .sessionOpen(true)
                .votesNo(0).votesYes(0)
                .build();
    }

    private int verifyMinSesion(VotingSessionDTO dto){
        return (dto.getMinDurationSession() != null) ? dto.getMinDurationSession() : 1;
    }
}
