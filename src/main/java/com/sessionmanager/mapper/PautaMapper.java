package com.sessionmanager.mapper;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.model.dto.PautaDTO;

import java.util.Date;

public class PautaMapper implements GenericMapper<Pauta, PautaDTO>{

    @Override
    public Pauta convertToEntity(PautaDTO dto) {
        return Pauta.builder().pautaTitle(dto.getPautaTitle())
                .pautaDescriptin(dto.getPautaDescriptin())
                .dtIns(new Date()).build();
    }
}
