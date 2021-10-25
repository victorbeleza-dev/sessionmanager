package com.sessionmanager.repository;

import com.sessionmanager.model.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionManagerRepository extends JpaRepository<VotingSession, Long> {
    VotingSession findByIdAndSessionOpen(Long id, boolean sessionOpen);
}
