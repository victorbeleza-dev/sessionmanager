package com.sessionmanager.service.impl;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.repository.PautaRepository;
import com.sessionmanager.service.PautaManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class PautaManagerServiceImpl implements PautaManagerService {

    @Autowired
    private PautaRepository repository;

    @Override
    public void registerPauta(Pauta pauta) throws Exception {
        verifyPautaExistByTitle(pauta);
        repository.save(pauta);
    }

    private void verifyPautaExistByTitle(Pauta pauta) throws Exception {
        if(repository.findPautaByPautaTitle(pauta.getPautaTitle()) != null)
            throw new DuplicateKeyException("Ja existe uma pauta com o mesmo titulo!");
    }

}
