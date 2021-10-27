package com.sessionmanager.controller;

import com.sessionmanager.model.dto.PautaDTO;
import com.sessionmanager.model.dto.VoteDTO;
import com.sessionmanager.repository.PautaRepository;
import com.sessionmanager.repository.VoteRepository;
import com.sessionmanager.service.PautaManagerService;
import com.sessionmanager.service.VoteSessionManagerService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class VoteControllerTest {

    @MockBean
    private VoteSessionManagerService service;

    @MockBean
    private VoteRepository repository;

    @InjectMocks
    private VoteController voteController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        standaloneSetup(this.voteController);
    }

    @Test
    public void mustReturnSuccess_WhenInsertingStaff() throws Exception {
        VoteDTO voteDTO = VoteDTO.builder().vote("NAO").idAssociated(1L).idSession(1L).CPF("27881442026").build();
        given()
                .contentType(ContentType.JSON)
                .body(voteDTO)
                .when()
                .post("/vote")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void mustReturnErrorBadRequest_WhenInsertWrongStaff(){
        VoteDTO voteDTO = VoteDTO.builder().idAssociated(1L).idSession(1L).CPF("27881442026").build();

        given()
                .contentType(ContentType.JSON)
                .body(voteDTO)
                .when()
                .post("/vote")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
