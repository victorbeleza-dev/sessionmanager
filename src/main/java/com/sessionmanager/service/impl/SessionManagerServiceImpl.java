package com.sessionmanager.service.impl;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.repository.PautaRepository;
import com.sessionmanager.service.SessionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionManagerServiceImpl implements SessionManagerService {

    @Autowired
    private PautaRepository repository;

    @Override
    public void registerPauta(Pauta pauta) {
        repository.save(pauta);
    }
}
