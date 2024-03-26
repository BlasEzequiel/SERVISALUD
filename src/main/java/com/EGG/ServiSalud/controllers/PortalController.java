package com.EGG.ServiSalud.controllers;

import com.EGG.ServiSalud.Enums.Especialidad;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PortalController {
    @Autowired
    private ProfesionalService profService;

    @GetMapping("/")
    public String home(ModelMap model){
        List<Profesional> profesionales = profService.listarProfesionales();
        model.addAttribute("profesionales", profesionales);
        return "index.html";
    }

    @GetMapping("/obtenerTodos")
    public void obtenerTodos(ModelMap model){
        List<Profesional> profesionales = profService.listarProfesionales();
        model.addAttribute("profesionales", profesionales);
    }

    @GetMapping("/buscarPorEspecialidad")
    public void buscarPorEspecialidad(@RequestParam Especialidad especialidad, ModelMap model){
        List<Profesional> profesionalesEspecialidad = profService.buscarPorEspecialidad(especialidad);
        model.addAttribute("profesionales", profesionalesEspecialidad);
    }
}
