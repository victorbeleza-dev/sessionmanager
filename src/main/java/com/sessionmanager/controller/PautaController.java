package com.sessionmanager.controller;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.service.PautaManagerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaManagerService service;

    public PautaController() {
    }

    public PautaController(PautaManagerService service) {
    }

    @ApiOperation(value = "Registra uma nova pauta")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pauta registrada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal feita"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity registerPauta(@Valid @RequestBody Pauta pauta) throws Exception {
        try{
            service.registerPauta(pauta);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            throw e;
        }
    }
}
