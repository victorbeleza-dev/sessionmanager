package com.sessionmanager.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VotingSessionDTO {

    @NotNull(message = "idPauta cannot be null")
    private Long idPauta;

    private Integer minDurationSession;
}
