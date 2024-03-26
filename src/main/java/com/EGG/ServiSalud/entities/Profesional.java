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
    @Column(name = "dias_disponibles")
    private List<DiasDisponibles> diasDisponibles;
    @Column(name = "horarios_disponibles")
    private List<LocalTime> horariosDisponibles;
    @Column(name = "agenda_no_disponible")
    private List<LocalDate> agendaNoDisp;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Profesional() {
    }

}