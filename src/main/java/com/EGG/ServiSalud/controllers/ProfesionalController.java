package com.EGG.ServiSalud.controllers;

import com.EGG.ServiSalud.Enums.Rol;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.exceptions.PacienteException;
import com.EGG.ServiSalud.exceptions.ProfesionalException;
import com.EGG.ServiSalud.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String loginProfesionales(){
        return "loginProfesionales.html";
    }
    @PostMapping("/login_profesionales")
    public String validarProfesional(@RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam Rol rol,
                                     ModelMap model) throws ProfesionalException {
        try {
            profService.validarInicioDeSesion(email,password);
            if (Rol.PROFESIONAL.equals(rol)){
                return "/index_profesional";
            }
            if (Rol.ADMIN.equals(rol)) {
                return "/index_admin";
            } else{
                throw new ProfesionalException("El campo ROL no debe estar vacío.");
            }

        }catch (ProfesionalException ex){
            model.put("error", ex.getMessage());
            return "/login_profesionales";
        }
    }

    @GetMapping("/index_profesional")
    public String indexProfesional(ModelMap model){
        return "indexProfesional.html";
    }
    @GetMapping("/index_admin")
    public String indexAdmin(ModelMap model){
        return "indexAdministrador.html";
    }

}
