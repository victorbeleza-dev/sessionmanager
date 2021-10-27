package com.sessionmanager.util;

import com.sessionmanager.repository.SessionManagerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateServicesUtil implements ValidateServiceUtil{

    @Autowired
    private SessionManagerRepository repository;

    @Override
    public void verifySessionExist(Long idSession) throws NotFoundException {
        if(repository.findByIdAndSessionOpen(idSession, true) == null)
            throw new NotFoundException("Não foi encontrado nenhuma sessão aberta com este idSession");
    }
}
