package com.sessionmanager.controller;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.model.Vote;
import com.sessionmanager.model.VotingSession;
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

    @GetMapping
    public String testOk() {
        return "ok";
    }

    @ApiOperation(value = "Cria uma nova sessão de votação")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sessão criada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal feita"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<?> openSessionVotes(@Valid @RequestBody VotingSession votingSession) throws NotFoundException {
        try{
            service.registerVotingSession(votingSession);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            throw e;
        }
    }

    @ApiOperation(value = "Encerra uma sessão de votação")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sessão criada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal feita"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    @PatchMapping("/{idSession}")
    public ResponseEntity<VotingSession> closeSessionVotes(@RequestParam Long idSession) throws NotFoundException {
        try{
            return new ResponseEntity<>(service.endVotingSession(idSession), HttpStatus.CREATED);
        }catch (Exception e){
            throw e;
        }
    }

    @ApiOperation(value = "Realiza a votação do associado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Voto registrado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição mal feita"),
            @ApiResponse(code = 404, message = "Não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    @PostMapping("/vote")
    public ResponseEntity voteSession(@Valid @RequestBody Vote vote) throws NotFoundException {
        try{
            service.registerVote(vote);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            throw e;
        }
    }

}
