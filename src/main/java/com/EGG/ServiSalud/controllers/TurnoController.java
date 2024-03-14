package com.EGG.ServiSalud.controllers;

import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.exceptions.TurnoException;
//import com.EGG.ServiSalud.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para operar
con la vista del usuario diseñada para la gestión de pacientes y profesionales (guardar/modificar, listar,
<<<<<<< HEAD
dar de baja, etc).*/
/*
=======
dar de baja, etc).
>>>>>>> f1285cc83007cf3b86d0bef24ab76274655b3e13
@Controller
@RequestMapping("/turnos") //localhost:8080/ServiSalud/turno
public class TurnoController {
    @Autowired
    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService){
        this.turnoService = turnoService;
    }

    @PostMapping("/crearTurno") //localhost:8080/ServiSalud/turno/crearTurno
    public String crearTurno(@RequestBody Paciente paciente, Profesional profesional, Integer horarioCita) throws TurnoException {
        Optional<Turno> optionalTurno = turnoService.obtenerTurnoPorHorario(horarioCita);
        Turno turno = new Turno();
        if(optionalTurno.isPresent()){
            turno = optionalTurno.orElse(new Turno());
        } else{
            throw new TurnoException("El turno no existe");
        }
        turno.setPaciente(paciente);
        turno.setDisponible(false);
        turnoService.completarTurno(turno, paciente.getIdPaciente());
        return("turnoCreado.html");
    }

    @GetMapping("/obtenerTurno")
    public ResponseEntity<List<Turno>> getAllTurnos(){
        List<Turno> personas = turnoService.obtenerTurnos();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> getPersonaById(@PathVariable Long id) throws TurnoException {
        Optional<Turno> optionalTurno = turnoService.obtenerTurnoID(id);
        Turno turno = optionalTurno.orElse(new Turno());
        if(turno != null){
            return new ResponseEntity<>(turno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


 */

