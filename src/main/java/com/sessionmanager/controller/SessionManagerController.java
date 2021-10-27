package com.sessionmanager.controller;

import com.sessionmanager.mapper.VotingSessionMapper;
import com.sessionmanager.model.Pauta;
import com.sessionmanager.model.Vote;
import com.sessionmanager.model.VotingSession;
import com.sessionmanager.model.dto.VotingSessionDTO;
import com.sessionmanager.service.SessionManagerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/session")
public class SessionManagerController {

    @Autowired
    private SessionManagerService service;

    private VotingSessionMapper votingSessionMapper = new VotingSessionMapper();

    public SessionManagerService getService() {
        return service;
    }

    public SessionManagerController() {
    }

    @ApiOperation(value = "Cria uma nova sessão de votação")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sessão criada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal feita"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity openSessionVotes(@Valid @RequestBody VotingSessionDTO votingSession) throws NotFoundException {
        try{
            service.registerVotingSession(votingSessionMapper.convertToEntity(votingSession));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            throw e;
        }
    }

    @ApiOperation(value = "Encerra uma sessão de votação")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sessão encerrada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal feita"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    @PatchMapping
    public ResponseEntity<VotingSession> closeSessionVotes(@RequestParam("idSession") Long idSession) throws NotFoundException {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.endVotingSession(idSession));
        }catch (Exception e){
            throw e;
        }
    }

}
