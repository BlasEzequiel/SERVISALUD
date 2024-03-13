package com.EGG.ServiSalud.persistent;

import com.EGG.ServiSalud.entities.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfesionalPersistent extends JpaRepository<Profesional,Long> {
//    @Query("SELECT * FROM profesionales WHERE especialidad= :especialidad")
//    Optional<Profesional> findById(String especialidad);
    @Query("INSERT INTO profesionales(nombre_completo) VALUES (:nombreProfesional) WHERE id_profesional=:idProfesional")
    public void actualizarNombreProfesional(String nombreProfesional, Long idProfesional);

}
