package com.sessionmanager.controller;

import com.sessionmanager.model.Pauta;
import com.sessionmanager.repository.PautaRepository;
import com.sessionmanager.repository.SessionManagerRepository;
import com.sessionmanager.repository.VoteRepository;
import com.sessionmanager.service.PautaManagerService;
import com.sessionmanager.service.SessionManagerService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest(PautaController.class)
@AutoConfigureMockMvc
public class PautaControllerTest {

    @MockBean
    private PautaManagerService service;

    @MockBean
    private PautaRepository repository;

    @InjectMocks
    private PautaController pautaController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        standaloneSetup(this.pautaController);
    }

    @Test
    public void mustReturnSuccess_WhenInsertingStaff() throws Exception {
//        Pauta pauta = Pauta.builder().pautaTitle("teste").pautaDescriptin("teste").build();

        given()
                .body("{\n" +
                        "  \"dtIns\": \"2021-10-25T22:59:34.351Z\",\n" +
                        "  \"id\": 0,\n" +
                        "  \"pautaDescriptin\": \"teste\",\n" +
                        "  \"pautaTitle\": \"teste\"\n" +
                        "}")
                .when()
                .post("/pauta")
                .then()
                .statusCode(HttpStatus.CREATED.value());
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//                .post("/pauta")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(String.valueOf(pauta));
//
//        mockMvc.perform(request)
//                .andExpect(MockMvcResultMatchers.status().is(201));
    }
}
