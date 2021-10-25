package com.sessionmanager.service;

import com.sessionmanager.model.Vote;
import com.sessionmanager.model.VotingSession;
import javassist.NotFoundException;

public interface SessionManagerService {

    void registerVotingSession(VotingSession votingSession) throws NotFoundException;

    void registerVote(Vote vote) throws NotFoundException;

    VotingSession endVotingSession(Long idSession) throws NotFoundException;
}
