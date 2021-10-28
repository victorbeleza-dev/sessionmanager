package com.sessionmanager.service.impl;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.repository.PautaRepository;
import com.sessionmanager.service.PautaManagerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaManagerServiceImpl implements PautaManagerService {

    @Autowired
    private PautaRepository repository;

    @Override
    public Pauta registerPauta(Pauta pauta) throws Exception {
        verifyPautaExistByTitle(pauta);
        return repository.save(pauta);
    }

    @Override
    public List<Pauta> findAllPauta() throws NotFoundException {
        if(repository.findAll().size() == 0)
            throw new NotFoundException("Não foi encontrada nenhuma lista");
        return repository.findAll();
    }

    private void verifyPautaExistByTitle(Pauta pauta) throws Exception {
        if(repository.findPautaByPautaTitle(pauta.getPautaTitle()) != null)
            throw new DuplicateKeyException("Ja existe uma pauta com o mesmo titulo!");
    }

}
