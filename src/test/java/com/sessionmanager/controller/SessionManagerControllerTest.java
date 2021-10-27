package com.sessionmanager.controller;

import com.sessionmanager.model.Vote;
import com.sessionmanager.model.VotingSession;
import com.sessionmanager.repository.SessionManagerRepository;
import com.sessionmanager.repository.VoteRepository;
import com.sessionmanager.service.SessionManagerService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class SessionManagerControllerTest {

    @MockBean
    private VoteRepository voteRepository;

    @MockBean
    private SessionManagerRepository repository;

    @MockBean
    private SessionManagerService service;

    @InjectMocks
    private SessionManagerController sessionManagerController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        standaloneSetup(this.sessionManagerController);
    }

    @Test
    public void mustReturnSuccess_WhenInsertingStaff() throws Exception {
        VotingSession votingSession = VotingSession.builder().idPauta(2L).build();

        given()
                .contentType(ContentType.JSON)
                .body(votingSession)
                .when()
                .post("/session")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void mustReturnErrorBadRequest_WhenInsertWrongStaff(){
        VotingSession votingSession = VotingSession.builder().idPauta(null).build();

        given()
                .contentType(ContentType.JSON)
                .body(votingSession)
                .when()
                .post("/session")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void mustReturnSuccess_WhenEndVotingSession() throws Exception {
        VotingSession votingSession = VotingSession.builder().idPauta(2L).build();

        given().param("idSession", 1)
                .when()
                .patch("/session")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void mustReturnError_WhenNotParamEndVotingSession() throws Exception {
        VotingSession votingSession = VotingSession.builder().idPauta(2L).build();

        given()
                .when()
                .patch("/session")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
