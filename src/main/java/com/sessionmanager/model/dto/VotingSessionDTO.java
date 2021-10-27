package com.sessionmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotingSessionDTO {

    @NotNull(message = "idPauta cannot be null")
    private Long idPauta;

    private Integer minDurationSession;
}
