package com.sessionmanager.service.impl;

import com.sessionmanager.model.Vote;
import com.sessionmanager.model.VotingSession;
import com.sessionmanager.repository.PautaRepository;
import com.sessionmanager.repository.SessionManagerRepository;
import com.sessionmanager.repository.VoteRepository;
import com.sessionmanager.service.SessionManagerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SessionManagerServiceImpl implements SessionManagerService {

    @Autowired
    private SessionManagerRepository repository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public VotingSession registerVotingSession(VotingSession votingSession) throws NotFoundException {
        verifyPautaExist(votingSession.getIdPauta());
        return repository.save(votingSession);
    }

    @Override
    public VotingSession endVotingSession(Long idSession) throws NotFoundException {
        verifySessionExist(idSession);
        VotingSession votingSession = verifyVotes(repository.getById(idSession));
        votingSession.setSessionOpen(false);
        return repository.save(votingSession);
    }

    private VotingSession verifyVotes (VotingSession votingSession){
        int votesYes = 0;
        int votesNo = 0;

        List<Vote> listVotes = voteRepository.findAllByIdSession(votingSession.getId());
        for(Vote vote : listVotes){
            if(Objects.equals(vote.getVote(), "SIM"))
                votesYes++;
            if(Objects.equals(vote.getVote(), "NAO"))
                votesNo++;
        }

        votingSession.setVotesYes(votesYes);
        votingSession.setVotesNo(votesNo);
        return votingSession;
    }

    private boolean verifyPautaExist(Long idPauta) throws NotFoundException {
        if(!pautaRepository.findById(idPauta).isPresent())
            throw new NotFoundException("N??o foi encontrada nenhuma pauta com esse id");
        else return true;
    }

    @Override
    public boolean verifySessionExist(Long idSession) throws NotFoundException {
        if(repository.findByIdAndSessionOpen(idSession, true) == null)
            throw new NotFoundException("N??o foi encontrado nenhuma sess??o aberta com este idSession");
        else return true;
    }
}
