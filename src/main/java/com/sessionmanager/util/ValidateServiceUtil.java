package com.sessionmanager.util;

import javassist.NotFoundException;

public interface ValidateServiceUtil {

    void verifySessionExist(Long idSession) throws NotFoundException;
}
