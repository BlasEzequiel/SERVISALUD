package com.EGG.ServiSalud.controllers;


import com.EGG.ServiSalud.Enums.*;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.exceptions.AdminException;
import com.EGG.ServiSalud.exceptions.PacienteException;
import com.EGG.ServiSalud.exceptions.ProfesionalException;
import com.EGG.ServiSalud.services.AdminService;
import com.EGG.ServiSalud.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.prefs.PreferencesFactory;

@Controller
public class ProfesionalController {
    @Autowired
    private ProfesionalService profService;
    @Autowired
    private AdminService adminService;

    public ProfesionalController(ProfesionalService profService, AdminService adminService){
        this.profService=profService;
        this.adminService=adminService;
    }
    @GetMapping("/login_profesionales")//localhost:8080/ServiSalud/login_profesionales
    public String loginProfesionales(ModelMap model) throws ProfesionalException {
//        List<DiasDisponibles> diasDisp = new ArrayList<>();
//        diasDisp.add(DiasDisponibles.LUNES);
//        diasDisp.add(DiasDisponibles.MARTES);
//        diasDisp.add(DiasDisponibles.MIERCOLES);
//
//        List<LocalTime> horarios = new ArrayList<>();
//        horarios.add(LocalTime.of(10, 30));
//        horarios.add(LocalTime.of(12, 00));
//        horarios.add(LocalTime.of(10, 30));
//
//        LocalDate fechaEspecifica = LocalDate.of(2022, 12, 31);
//        profService.createProfesional("lihuel","amarula", fechaEspecifica, Genero.MASCULINO, "cerebro@chamiprof.com", "222222", "222222", "123432545", 1232131L, 4234324, Especialidad.CARDIOLOGIA, 123.0, diasDisp, horarios , "lorem impusn o como se escriba esta es una descripcion para la validacion de crear profesional");
//        profService.createProfesional("macarena","lopez", fechaEspecifica, Genero.OTRO, "chami@mmmmm.com", "666666", "666666", "123432545", 1232131L, 4234324, Especialidad.ODONTOLOGIA, 123.0, diasDisp, horarios , "el mundo del reves sobre los pies esta es una descripcion para la validacion de crear profesional");
//        profService.createProfesional("sebastian","perez", fechaEspecifica, Genero.FEMENINO, "chami@xxxxx.com", "000000", "000000", "123432545", 1232131L, 4234324, Especialidad.CLINICO, 123.0, diasDisp, horarios , "quiero helado esta es una descripcion para la validacion de crear profesional");
        model.addAttribute("roles", Rol.values());
        return "loginProfesionales.html";
    }
    @PostMapping("/login_profesionales")
    public ResponseEntity<String> validarProfesional(@RequestParam String email,
                                                     @RequestParam String password,
                                                     @RequestParam(value = "rol", required = false) Rol rol,
                                                     ModelMap model,
                                                     RedirectAttributes redirectAttributes) {
        try {
            if (Rol.PROFESIONAL.equals(rol)) {
                Profesional profesional = profService.validarInicioDeSesion(email, password);
                return ResponseEntity.ok().body("/index_profesional/" + profesional.getIdPersona());
            } else if (Rol.ADMIN.equals(rol)) {
                adminService.iniciarAdmin(email, password);
                return ResponseEntity.ok().body("/index_admin");
            } else {
                redirectAttributes.addFlashAttribute("error", "Rol no válido");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("/login_profesionales");
            }
        } catch (ProfesionalException | AdminException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("/login_profesionales");
        }
    }

    @GetMapping("/index_profesional/{id}")
    public String indexProfesional(@PathVariable("id") Long id, ModelMap model){
        Optional<Profesional> optional = profService.buscarPorId(id);
        if(optional.isPresent()){
            Profesional profesional = optional.get();
            model.put("profesional", profesional);
        }
        return "indexProfesional.html";
    }
    @GetMapping("/index_admin")
    public String indexAdmin(ModelMap model){
        List<Profesional> listaProfesionales = profService.listarProfesionales();
        return "indexAdministrador.html";
    }


    @PostMapping("/crear_profesional")
    public String crearProfesional(@RequestParam(required=false) String nombre, @RequestParam(required=false) String apellido,
                                   @RequestParam(required=false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento,
                                   @RequestParam(required=false) Genero genero, @RequestParam(required=false) String mail,
                                   @RequestParam(required=false) String password, @RequestParam(required=false) String password2,
                                   @RequestParam(required=false) String phone, @RequestParam(required=false) Long dni,
                                   @RequestParam(required=false) Integer matricula, @RequestParam(required=false) Especialidad especialidad,
                                   @RequestParam(required=false) Double valorConsulta, @RequestParam(required=false) List<DiasDisponibles> diasDisponibles,
                                   @RequestParam(required=false) List<LocalTime> horariosDisponibles,@RequestParam(required=false) String descripcion,
                                   ModelMap model){
        try {
//            List<DiasDisponibles> diasDisp = new ArrayList<>();
//            diasDisp.add(DiasDisponibles.LUNES);
//            diasDisp.add(DiasDisponibles.MARTES);
//            diasDisp.add(DiasDisponibles.MIERCOLES);
//
//            List<LocalTime> horarios = new ArrayList<>();
//            horarios.add(LocalTime.of(10, 30));
//            horarios.add(LocalTime.of(12, 00));
//            horarios.add(LocalTime.of(10, 30));
//
//            LocalDate fechaEspecifica = LocalDate.of(2022, 12, 31);
//            profService.createProfesional("camila","perez", fechaEspecifica, Genero.FEMENINO, "chami@chamiprof.com", "123456", "123456", "123432545", 1232131L, 4234324, Especialidad.CLINICO, 123.0, diasDisp, horarios );
            profService.createProfesional(nombre, apellido, fechaNacimiento, genero, mail, password, password2, phone, dni, matricula, especialidad, valorConsulta, diasDisponibles,horariosDisponibles, descripcion);
            model.put("exito", "El profesional ha sido creado con éxito.");
            return "/index_admin";
        }catch (ProfesionalException ex){
            model.put("error", ex.getMessage());
            return "/register";
        }
    }
}
