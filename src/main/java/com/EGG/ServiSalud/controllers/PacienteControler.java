package com.EGG.ServiSalud.controllers;

import com.EGG.ServiSalud.Enums.CoberturaMedica;
import com.EGG.ServiSalud.Enums.Especialidad;
import com.EGG.ServiSalud.Enums.Genero;
import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.exceptions.PacienteException;
import com.EGG.ServiSalud.services.PacienteService;
import com.EGG.ServiSalud.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class PacienteControler {
    @Autowired
    private PacienteService pacienteServicio;
    @Autowired
    private ProfesionalService profService;

    @GetMapping("/register")//localhost:8080/serviSalud/register
    public String registrar(ModelMap model) {
        model.addAttribute("generos", Genero.values());
        model.addAttribute("coberturas", CoberturaMedica.values());
        return "register.html";
    }

    @PostMapping("/register")
    public String registro(@RequestParam(required=false) String nombre, @RequestParam(required=false) String apellido,
                           @RequestParam(required=false) CoberturaMedica coberturaMedica, @RequestParam(required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento,
                           @RequestParam(required=false) Genero genero, @RequestParam(required=false) String mail,
                           @RequestParam(required=false) String password,@RequestParam(required=false) String password2,
                           @RequestParam(required=false) String phone, @RequestParam(required=false) Long dni,
                           ModelMap model) {
        try {
            pacienteServicio.CrearPaciente(nombre, apellido,coberturaMedica,fechaNacimiento,genero, mail, password,password2, phone, dni);
            model.put("exito", "El paciente ha sido creado con Ã©xito. Por favor ingrese sus datos para acceder");
            return "/login";
        } catch (PacienteException ex) {
            model.put("error", ex.getMessage());
            model.addAttribute("generos", Genero.values());
            model.addAttribute("coberturas", CoberturaMedica.values());
            return "/register";
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String logearPaciente(@RequestParam String email,
                                 @RequestParam String password, ModelMap model){
        try {
            Paciente paciente = pacienteServicio.validarInicioSesion(email,password);
            model.put("paciente", paciente);
            return "redirect:/index_paciente/" + paciente.getIdPersona();

        }catch (PacienteException ex){
            model.put("error", ex.getMessage());
            return "login";
        }
    }
    @GetMapping("/index_paciente/{id}")
    public String indexPaciente(@PathVariable Long id, ModelMap model) {
        Optional<Paciente> optional = pacienteServicio.buscarPorId(id);
        if (optional.isPresent()) {
            Paciente paciente = optional.get();
            model.put("paciente", paciente);
        }
        List<Profesional> profesionales = profService.listarProfesionales();
        model.addAttribute("profesionales", profesionales);
        return "indexUsuario.html";
    }

    @GetMapping("/listarProfesionalesPorEspecialidad")
    public String buscarPorEspecialidad(@RequestParam Especialidad especialidad, ModelMap model){
        List<Profesional> listaProfesionales = profService.buscarPorEspecialidad(especialidad);
        model.addAttribute("profesionales", listaProfesionales);
        return "/index_paciente";
    }
    @GetMapping("/crear_turno/{id}")
    public String crearTurno(@PathVariable Long id, ModelMap model){
        return "";
    }

}