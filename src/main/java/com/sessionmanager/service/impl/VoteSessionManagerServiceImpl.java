package com.sessionmanager.service.impl;

import com.sessionmanager.model.Vote;
import com.sessionmanager.repository.VoteRepository;
import com.sessionmanager.service.SessionManagerService;
import com.sessionmanager.service.VoteSessionManagerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class VoteSessionManagerServiceImpl implements VoteSessionManagerService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private SessionManagerService sessionManagerService;

    @Override
    public void registerVote(Vote vote) throws NotFoundException {
        sessionManagerService.verifySessionExist(vote.getIdSession());
        verifyAlreadyVoted(vote);
        voteRepository.save(vote);
    }

    private void verifyAlreadyVoted(Vote vote){
        if (voteRepository.findByIdAssociatedAndIdSession(vote.getIdAssociated(), vote.getIdSession()) != null)
            throw new DuplicateKeyException("Esse id do associado ja votou nesta sessão");
    }
}
