package com.EGG.ServiSalud.services;

import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.entities.Turno;
import com.EGG.ServiSalud.exceptions.TurnoException;
import com.EGG.ServiSalud.persistent.PacientePersistent;
import com.EGG.ServiSalud.persistent.TurnoPersistent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
   /* @Autowired
    private TurnoPersistent turnoPer;
    @Autowired
    private PacientePersistent pacientePer;

    @Transactional
    public void actualizarTurno(Long idPaciente, Long idTurno){
        Turno turno = turnoPer.getById(idTurno);
        Paciente paciente = pacientePer.getById(idPaciente);
        turno.setPaciente(paciente);
        turno.setDisponible(false);
    }

    public List<Turno> obtenerTurnos(){
        return turnoPer.findAll();
    }
    public Turno obtenerTurno(Long idTurno){
        return turnoPer.getById(idTurno);
    }
    public List<Turno> obtenerTurnosDePaciente(Long idPaciente){
        Paciente paciente = pacientePer.getById(idPaciente);
        return turnoPer.buscarTurnosPorPaciente(paciente);
    }

    @Transactional
    public String cancelarTurno(Long idPersona, Long idTurno){
        Paciente paciente = pacientePer.getById(idPersona);
        Turno turno = turnoPer.getById(idTurno);
        List<Turno> turnos = paciente.getListaTurnos();
        for(Turno t : turnos){
            if(t.equals(turno)){
                turnos.remove(t);
            }
        }
        return "Turno eliminado con exito";
    }*/



}