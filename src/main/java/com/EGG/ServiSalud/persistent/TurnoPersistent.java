package com.EGG.ServiSalud.persistent;

import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.entities.Turno;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*Contiene los métodos necesarios para guardar/actualizar en la base de datos, realizar consultas o dar de baja
según corresponda
*/
@Repository
@Transactional
public interface TurnoPersistent extends JpaRepository<Turno, Long> {

    /*Obtener turnos según Profesional*/
    @Query("SELECT t FROM turnos WHERE t.profesional = :idProfesional")
    List<Turno> findByProfesional(Long id_profesional);

    /*Obtener turnos según Paciente*/
    @Query("SELECT t FROM turnos WHERE t.paciente = :idPaciente")
    List<Turno> findByPaciente(Long id_paciente);

    /*Obtener turno según horario
    * ACOMODAR: Se debe recibir también el Id del profesional en cuestión además de la disponibilidad del turno*/
    @Query("SELECT t FROM turnos WHERE t.horario = :horario")
    Optional<Turno> findByHorario(Integer horario);

    /*Obtener Listado de turnos según disponibilidad
    * ACOMODAR: Se debe recibir también el Id del profesional en cuestión además de la disponibilidad del turno*/
    @Query("SELECT t FROM turnos WHERE t.disponible = :disponible")
    List<Turno> findByDisponibles(Long idProfesional, Boolean disponible);

    /*Obtener listado de turnos según realizado*/
    @Query("SELECT t FROM turnos WHERE t.realizado = :realizado")
    List<Turno> findByRealizado(Boolean realizado);

    /*Actualizar datos*/
    @Query("INSERT INTO turnos(paciente, disponible) VALUES (:idpaciente, :disponible WHERE idTurno = :idTurno")
    void actualizarTurno(Long idTurno, Long idPaciente, Boolean disponible);

    /*Cambiar el estado de realizado del turno*/
    @Query("UPDATE turnos SET realizado = :realizado WHERE idTurno = :idTurno" )
    void cambiarEstadoDeRealizado(Boolean realizado, Long idTurno);

}
