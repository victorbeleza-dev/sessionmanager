package com.sessionmanager.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PautaDTO {

    @NotBlank(message = "pautaDescriptin cannot be null")
    private String pautaDescriptin;

    @NotBlank(message = "pautaTitle cannot be null")
    private String pautaTitle;
}
