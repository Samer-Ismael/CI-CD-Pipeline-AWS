package com.frontend.CatFrontend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerMessage {

    @GetMapping("")
    public String getServerMessage() {
        return "This is the server message!";
    }

}
