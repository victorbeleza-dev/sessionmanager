package com.sessionmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {

    @NotNull(message = "idPauta cannot be null")
    private Long idAssociated;

    @NotNull(message = "idSession cannot be null")
    private Long idSession;

    @NotBlank(message = "vote cannot be null")
    private String vote;

    @NotBlank(message = "CPF cannot be null")
    private String CPF;
}
