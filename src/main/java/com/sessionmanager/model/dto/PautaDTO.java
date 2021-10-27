package com.sessionmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO {

    @NotBlank(message = "pautaDescriptin cannot be null")
    private String pautaDescriptin;

    @NotBlank(message = "pautaTitle cannot be null")
    private String pautaTitle;
}
