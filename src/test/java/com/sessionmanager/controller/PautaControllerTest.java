package com.sessionmanager.controller;

import com.sessionmanager.model.dto.PautaDTO;
import com.sessionmanager.repository.PautaRepository;
import com.sessionmanager.service.PautaManagerService;
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
public class PautaControllerTest {

    @MockBean
    private PautaManagerService service;

    @MockBean
    private PautaRepository repository;

    @InjectMocks
    private PautaController pautaController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        standaloneSetup(this.pautaController);
    }

    @Test
    public void mustReturnSuccess_WhenInsertingStaff() throws Exception {
        PautaDTO pautaDTO = PautaDTO.builder().pautaTitle("teste").pautaDescriptin("teste").build();

        given()
                .contentType(ContentType.JSON)
                .body(pautaDTO)
                .when()
                .post("/pauta")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void mustReturnErrorBadRequest_WhenInsertWrongStaff(){
        PautaDTO pautaDTO = PautaDTO.builder().pautaDescriptin("teste").build();

        given()
                .contentType(ContentType.JSON)
                .body(pautaDTO)
                .when()
                .post("/pauta")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void mustReturnSuccess_WhenFindAlPauta() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/pauta")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
