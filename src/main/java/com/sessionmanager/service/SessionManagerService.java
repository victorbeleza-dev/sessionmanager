package com.sessionmanager.service;

import com.sessionmanager.model.VotingSession;
import javassist.NotFoundException;

public interface SessionManagerService {

    VotingSession registerVotingSession(VotingSession votingSession) throws NotFoundException;

    VotingSession endVotingSession(Long idSession) throws NotFoundException;

    boolean verifySessionExist(Long idSession) throws NotFoundException;
}
