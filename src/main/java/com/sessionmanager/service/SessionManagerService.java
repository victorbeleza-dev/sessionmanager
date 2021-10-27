package com.sessionmanager.service;

import com.sessionmanager.model.VotingSession;
import javassist.NotFoundException;

public interface SessionManagerService {

    void registerVotingSession(VotingSession votingSession) throws NotFoundException;

    VotingSession endVotingSession(Long idSession) throws NotFoundException;

    void verifySessionExist(Long idSession) throws NotFoundException;
}
