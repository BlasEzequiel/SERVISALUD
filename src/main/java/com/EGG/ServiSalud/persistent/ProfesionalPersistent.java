package com.EGG.ServiSalud.persistent;

import com.EGG.ServiSalud.entities.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProfesionalPersistent extends JpaRepository<Profesional,Long> {
}
