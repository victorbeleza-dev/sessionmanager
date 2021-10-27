package com.sessionmanager.service;

import com.sessionmanager.model.Vote;
import javassist.NotFoundException;

public interface VoteSessionManagerService {

    Vote registerVote(Vote vote) throws Exception;
}
