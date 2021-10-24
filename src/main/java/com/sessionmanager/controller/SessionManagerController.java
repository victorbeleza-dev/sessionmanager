package com.sessionmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pauta")
public class SessionManagerController {

    @GetMapping
    public String toString() {
        return "ok";
    }

    @PostMapping
    public String registerPauta(){
        return "ok";
    }
}
