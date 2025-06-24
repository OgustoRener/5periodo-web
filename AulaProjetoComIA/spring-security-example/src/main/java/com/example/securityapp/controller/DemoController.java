package com.example.securityapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/public")
    public String publicRoute() {
        return "<h1>Rota Pública</h1><p>Acessível por todos!</p>";
    }

    @GetMapping("/private")
    public String privateRoute() {
        return "<h1>Rota Privada</h1><p>Acessível apenas por usuários autenticados.</p>";
    }
}