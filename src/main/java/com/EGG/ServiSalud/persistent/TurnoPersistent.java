package com.EGG.ServiSalud.persistent;
import com.EGG.ServiSalud.Enums.DiasDisponibles;
import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoPersistent extends JpaRepository<Turno,Long> {
    @Query("SELECT * FROM Turno t WHERE t.paciente = :paciente")
    List<Turno> buscarTurnosPorPaciente(@Param("paciente") Paciente paciente);

    @Query("SELECT * FROM Turno t WHERE t.profesional = :profesional")
    List<Turno> buscarTurnosPorProfesional(@Param("profesional") Profesional profesional);

    @Query("SELECT * FROM Turno t WHERE t.fechaYHorarioTurno.diaDisponibles = :diaDisponible ")
    List<Turno> buscarTurnosPorDia(@Param("diaDisponible") DiasDisponibles diaDisponible);

}