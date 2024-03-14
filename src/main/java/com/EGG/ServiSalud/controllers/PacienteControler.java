package com.EGG.ServiSalud.controllers;

import com.EGG.ServiSalud.Enums.CoberturaMedica;
import com.EGG.ServiSalud.Enums.Genero;
import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.exceptions.PacienteException;
import com.EGG.ServiSalud.services.PacienteServices;
import com.EGG.ServiSalud.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PacienteControler {
    @Autowired
    private PacienteServices pacienteServicio;
    @Autowired
    private ProfesionalService profService;

    @GetMapping("/register")//localhost:8080/serviSalud/register
    public String registrar(ModelMap model) {
        model.addAttribute("generos", Genero.values());
        model.addAttribute("coberturas", CoberturaMedica.values());
        return "register.html";
    } //Fin GETMAPPING REGISTER
    @PostMapping("/register")
    public String registro(@RequestParam String nombre, @RequestParam String apellido,
                           @RequestParam String coberturaMedica, @RequestParam Date fechaNacimiento,
                           @RequestParam String genero, @RequestParam String mail,
                           @RequestParam String password,@RequestParam String password2,
                           @RequestParam String phone, @RequestParam Long dni,
                           ModelMap model) {
        try {
            pacienteServicio.CrearPaciente(nombre, apellido, coberturaMedica, mail, password,password2);
            model.put("exito", "El paciente ha sido creado con éxito, por favor ingrese sus datos para acceder");
            return "/login";
        } catch (PacienteException ex) {
            model.put("error", ex.getMessage());
            return "/register";
        }
    } //Fin POSTMAPPING REGISTER

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @PostMapping("/login")
    public String logearPaciente(@RequestParam String email,
                                 @RequestParam String password, ModelMap model){
        try {
            pacienteServicio.validarInicioSesion(email,password);
            return "redirect:../index_paciente";
        }catch (PacienteException ex){
            model.put("error", ex.getMessage());
            return "login.html";
        }
    }
    @GetMapping("/index_paciente/{id}")
    public String indexPaciente(@PathVariable Long id, ModelMap model){
        Optional<Paciente> optional = pacienteServicio.buscarPorId(id);
        if(optional.isPresent()){
            Paciente paciente = optional.get();
            model.put("paciente", paciente);
        }
        List<Profesional> profesionales = profService.listarProfesionales();
        model.addAttribute("profesionales", profesionales);
        return "indexUsuario.html";
    }


    @GetMapping("/crear_turno/{id}")
    public String crearTurno(@PathVariable Long id, ModelMap model){
        return "";
    }

}
