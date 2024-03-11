package com.EGG.ServiSalud.controllers;

import com.EGG.ServiSalud.exceptions.PacienteException;
import com.EGG.ServiSalud.services.PacienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/paciente")//localhost:8080/serviSalud/paciente
public class PacienteControler {
    @Autowired
    private PacienteServices PacienteServicio;


    @GetMapping("/registrar")//localhost:8080/paciente/registrar
    public String registrar(){
        return "paciente_formulario.html";
    }


    @PostMapping("/registro")
    public String registro(@RequestParam String nombreCompleto, @RequestParam Boolean coberturaMedica,
                           @RequestParam Date fechaNacimiento, @RequestParam Boolean genero, @RequestParam String mail,
                           @RequestParam  String password, @RequestParam String phone) throws PacienteException {

        PacienteServicio.CrearPaciente(nombreCompleto, coberturaMedica, fechaNacimiento, genero, mail, password, phone);



        //System.out.println("Nombre: " + nombre);
        return "paciente_formulario.html";
    }

}
