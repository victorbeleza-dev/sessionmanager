package com.sessionmanager.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VoteDTO {

    @NotNull(message = "idPauta cannot be null")
    private Long idAssociated;

    @NotNull(message = "idSession cannot be null")
    private Long idSession;

    @NotBlank(message = "vote cannot be null")
    private String vote;
}
