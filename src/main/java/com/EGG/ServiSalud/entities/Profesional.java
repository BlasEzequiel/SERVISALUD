package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.entities.Enums.Especialidad;
import jakarta.persistence.*;


@Entity
@Table(name = "profesionales")

public class Profesional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="idProfesional")
    private Long idProfesional;
    private Especialidad especialidad;
    private Double reputacion;
    private Double valorConsultaConCS;
    private Double valorConsultaSinCS;

}
