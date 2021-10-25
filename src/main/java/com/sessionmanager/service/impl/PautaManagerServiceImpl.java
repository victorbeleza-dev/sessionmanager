package com.sessionmanager.service.impl;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.repository.PautaRepository;
import com.sessionmanager.service.PautaManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PautaManagerServiceImpl implements PautaManagerService {

    @Autowired
    private PautaRepository repository;

    @Override
    public void registerPauta(Pauta pauta) throws Exception {
        verifyPautaExistByTitle(pauta);
        repository.save(assembleObjectPauta(pauta));
    }

    private void verifyPautaExistByTitle(Pauta pauta) throws Exception {
        if(repository.findPautaByPautaTitle(pauta.getPautaTitle()) != null)
            throw new DuplicateKeyException("Ja existe uma pauta com o mesmo titulo!");
    }

    private Pauta assembleObjectPauta(Pauta pauta){
        return Pauta.builder().pautaTitle(pauta.getPautaTitle())
                .pautaDescriptin(pauta.getPautaDescriptin())
                .dtIns(new Date()).build();
    }
}
