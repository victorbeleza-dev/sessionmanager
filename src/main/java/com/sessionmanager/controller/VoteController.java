package com.sessionmanager.controller;

import com.sessionmanager.mapper.VoteMapper;
import com.sessionmanager.model.Vote;
import com.sessionmanager.model.dto.VoteDTO;
import com.sessionmanager.service.VoteSessionManagerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteSessionManagerService service;

    private final VoteMapper voteMapper = new VoteMapper();

    @ApiOperation(value = "Realiza a votação do associado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Voto registrado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal feita"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<Vote> voteSession(@Valid @RequestBody VoteDTO vote) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.registerVote(voteMapper.convertToEntity(vote)));
        }catch (Exception e){
            throw e;
        }
    }
}
