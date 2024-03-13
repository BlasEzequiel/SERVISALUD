package com.EGG.ServiSalud.persistent;

import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientePersistent extends JpaRepository<Paciente, Long> {


    //Obtener paciente de acuerdo a DNI y al nombre





}
