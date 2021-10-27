package com.sessionmanager.service.impl;

import com.sessionmanager.client.IsAbleToVoteClient;
import com.sessionmanager.model.Vote;
import com.sessionmanager.repository.VoteRepository;
import com.sessionmanager.service.SessionManagerService;
import com.sessionmanager.service.VoteSessionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VoteSessionManagerServiceImpl implements VoteSessionManagerService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private SessionManagerService sessionManagerService;

    @Override
    public Vote registerVote(Vote vote) throws Exception {
        verifyIsAbleToVote(vote.getCPF());
        sessionManagerService.verifySessionExist(vote.getIdSession());
        verifyAlreadyVoted(vote);
        return voteRepository.save(vote);
    }

    boolean verifyAlreadyVoted(Vote vote){
        if (voteRepository.findByIdAssociatedAndIdSession(vote.getIdAssociated(), vote.getIdSession()) != null)
            throw new DuplicateKeyException("Esse id do associado ja votou nesta sessão");
        else
            return true;
    }

    private void verifyIsAbleToVote(String cpf) throws Exception {
        IsAbleToVoteClient isAbleToVote = new IsAbleToVoteClient();
        if (Objects.equals(isAbleToVote.findIsAbleToVoteByCpf(cpf).getStatus(), "UNABLE_TO_VOTE"))
            throw new IllegalAccessException("Este CPF não está válido para a votação");
    }
}
