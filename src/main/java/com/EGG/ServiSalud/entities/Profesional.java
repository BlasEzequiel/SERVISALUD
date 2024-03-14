package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.Enums.DiasDisponibles;
import com.EGG.ServiSalud.Enums.Especialidad;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "profesionales")
@Getter
@Setter
public class Profesional extends Persona {
    private Integer matricula;
    private Especialidad especialidad;
    private Double reputacion;
    private Double valorConsulta;
    @Column(name = "dias_disponibles")
    private List<DiasDisponibles> diasDisponibles;
    @Column(name = "horarios_disponibles")
    private List<Date> horariosDisponibles;
    @Column(name = "agenda_no_disponible")
    private List<Date> agendaNoDisp;

    public Profesional() {
    }

}
