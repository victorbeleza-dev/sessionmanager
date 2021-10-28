package com.sessionmanager.controller;

import com.sessionmanager.mapper.PautaMapper;
import com.sessionmanager.mapper.VoteMapper;
import com.sessionmanager.model.Pauta;
import com.sessionmanager.model.dto.PautaDTO;
import com.sessionmanager.service.PautaManagerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaManagerService service;

    private final PautaMapper pautaMapper = new PautaMapper();

    public PautaController(PautaManagerService service) {
        this.service = service;
    }

    public PautaController() {
    }

    @ApiOperation(value = "Registra uma nova pauta")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pauta registrada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal feita"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity registerPauta(@Valid @RequestBody PautaDTO pauta) throws Exception {
        try{
            service.registerPauta(pautaMapper.convertToEntity(pauta));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            throw e;
        }
    }

    @ApiOperation(value = "Busca todas as pautas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de pautas"),
            @ApiResponse(code = 400, message = "Requisição mal feita"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<Pauta>> findAllPautas() throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllPauta());
        }catch (Exception e){
            throw e;
        }
    }
}
