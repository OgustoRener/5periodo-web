package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("message", "Eu perdi o meu medo meu medo meu medo da chuva");
        return "home";  
    }
}
