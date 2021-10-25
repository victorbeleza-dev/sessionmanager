package com.sessionmanager.repository;

import com.sessionmanager.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findByIdAssociatedAndIdSession(Long idAssociated, Long idSession);

    List<Vote> findAllByIdSession(Long idSession);
}
