package com.EGG.ServiSalud.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") //localhost:8080/ServiSalud/
public class PortalController {
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
}
