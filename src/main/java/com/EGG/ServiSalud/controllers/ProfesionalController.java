package com.EGG.ServiSalud.controllers;

import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.exceptions.ProfesionalException;
import com.EGG.ServiSalud.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ProfesionalController {
    @Autowired
    private ProfesionalService profService;
    public ProfesionalController(ProfesionalService profService){
        this.profService=profService;
    }
    @GetMapping("/login_profesionales")//localhost:8080/ServiSalud/login_profesionales
    public String registrarProfesional(){
        return "loginProfesionales.html";
    }
    @PostMapping("/login_profesionales")
    public String validarProfesional(@RequestParam(required = false) String email, @RequestParam(required = false) String password) throws ProfesionalException {
        validarDatos(email, password);
        Profesional profesional = new Profesional();

        return "index.html";
    }
    public void validarDatos( String email, String password) throws ProfesionalException {
        if(email.isEmpty()){
            throw new ProfesionalException("El e-mail no puede estar vacío.");
        }
        if(password.isEmpty()){
            throw new ProfesionalException("El password no puede estar vacío.");
        }
    }


}
