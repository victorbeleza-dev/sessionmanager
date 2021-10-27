package com.sessionmanager.client;

import com.sessionmanager.model.AbleToVote;
import org.springframework.web.client.RestTemplate;

public class IsAbleToVoteClient {

    static String webService = "https://user-info.herokuapp.com/users/";
    static int codigoSucesso = 200;

    public AbleToVote findIsAbleToVoteByCpf(String cpf) throws Exception {
        String urlParaChamada = webService + cpf;

        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(urlParaChamada, AbleToVote.class);
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
}
