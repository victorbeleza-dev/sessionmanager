package com.sessionmanager.controller;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.service.SessionManagerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pauta")
public class SessionManagerController {

    @Autowired
    private SessionManagerService service;

    @GetMapping
    public String testOk() {
        return "ok";
    }

    @ApiOperation(value = "Registra uma nova pauta")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pauta registrada com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity registerPauta(@Valid @RequestBody Pauta pauta){
//        String title = null;
//        int titleLength = title.length();

        throw new RuntimeException();
//        service.registerPauta(pauta);
        //return new ResponseEntity(HttpStatus.CREATED);
    }
}
