package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.Enums.DiasDisponibles;
import com.EGG.ServiSalud.Enums.Especialidad;
import com.EGG.ServiSalud.Enums.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "profesionales")
@Getter
@Setter
public class Profesional extends Persona {

    private Integer matricula;
    private Especialidad especialidad;
    private String descripcion;
    private Double reputacion;
    private Double valorConsulta;
    private String foto;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Profesional() {
    }

}