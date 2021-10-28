package com.sessionmanager.service;

import com.sessionmanager.model.Pauta;
import javassist.NotFoundException;

import java.util.List;

public interface PautaManagerService {

    Pauta registerPauta(Pauta pauta) throws Exception;

    List<Pauta> findAllPauta() throws NotFoundException;
}
