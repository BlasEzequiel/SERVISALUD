package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.Enums.CoberturaMedica;
import com.EGG.ServiSalud.Enums.Rol;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "pacientes")
@Getter
@Setter
public class Paciente extends Persona {

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(name = "cobertura_medica")
    @Enumerated(EnumType.STRING)
    private CoberturaMedica coberturaMedica;

    public Paciente() {
        super();
    }
}