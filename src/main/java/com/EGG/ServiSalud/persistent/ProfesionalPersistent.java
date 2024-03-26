package com.EGG.ServiSalud.persistent;

import com.EGG.ServiSalud.Enums.Especialidad;
import com.EGG.ServiSalud.entities.Persona;
import com.EGG.ServiSalud.entities.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProfesionalPersistent extends JpaRepository<Profesional,Long> {
    @Query("SELECT p FROM Profesional p WHERE p.mail= :mail")
    Optional<Profesional> buscarPorMail(@Param("mail")String mail);

    @Query("SELECT p FROM Profesional p WHERE p.especialidad = :especialidad")
    List<Profesional> buscarPorEspecialidad(@Param("especialidad") Especialidad especialidad);


//    @Query("SELECT p FROM profesional WHERE p.id_persona= :id")
//    public Profesional obtenerProfesionalPorIdPersona(@Param("id")Long id);
}