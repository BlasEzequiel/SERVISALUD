package com.EGG.ServiSalud.services;

import com.EGG.ServiSalud.entities.Enums.HorarioCita;
import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.entities.Turno;
import com.EGG.ServiSalud.exceptions.TurnoException;
import com.EGG.ServiSalud.persistent.TurnoPersistent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/*Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
administrar (consulta, creación, modificación y dar de baja).*/
@Service
public class TurnoService {
    @Autowired
    private TurnoPersistent turnoPer;

    /*Crear turno*/
    @Transactional
    public Turno crearTurno(Turno turno) {
        return turnoPer.save(turno);
    }

    /*Obtener todos los turnos*/
    public List<Turno> obtenerTurnos(){
        return turnoPer.findAll();
    }

    /*Obtener turno por ID*/
    public Optional<Turno> obtenerTurnoID(Long id) throws TurnoException{
        Optional<Turno> turno = turnoPer.findById(id);
        if(turno.isPresent()){
            return turno;
        } else{
            throw new TurnoException("El turno no existe");
        }
    }

    /*Obtener turno por Horario*/
    public Optional<Turno> obtenerTurnoPorHorario(Integer horario) throws TurnoException {
        Optional<Turno> turno = turnoPer.findByHorario(horario);
        if(turno.isPresent()){
            return turno;
        } else {
            throw new TurnoException("El turno no existe");
        }
    }

    /*Agregar Paciente a turno*/
    @Transactional
    public void completarTurno(Turno turno, Long idPaciente){
        turnoPer.actualizarTurno(turno.getIdTurno(), idPaciente, turno.getDisponible() );
    }

    /*Cambiar el estado de realizado del turno*/
    public void finalizarTurno(Turno turno){
        turnoPer.cambiarEstadoDeRealizado(turno.getRealizado(), turno.getIdTurno());
    }





}
