package com.sessionmanager.repository;

import com.sessionmanager.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    Pauta findPautaByPautaTitle(String title);
}
