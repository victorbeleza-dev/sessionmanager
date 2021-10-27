package com.sessionmanager.service;

import com.sessionmanager.model.Vote;
import javassist.NotFoundException;

public interface VoteSessionManagerService {

    void registerVote(Vote vote) throws NotFoundException;
}
