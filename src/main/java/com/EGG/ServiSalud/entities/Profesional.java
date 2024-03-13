/*package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.Enums.Especialidad;
import com.EGG.ServiSalud.Enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "profesionales")
public class Profesional extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id_profesional")
    private Long idProfesional;

    private Long idPersona;
    @Getter
    @Setter
    @Column(name = "matricula")
    private int matricula;
    @Getter
    @Setter
    @Column(name = "especialidad")
    private Especialidad especialidad;
    @Getter
    @Setter
    @Column(name = "reputacion")
    private Double reputacion;
    @Getter
    @Setter
    @Column(name = "valor_consulta_con_cs")
    private Double valorConsultaConCS;
    @Getter
    @Setter
    @Column(name = "valor_consulta_sin_cs")
    private Double valorContulaSinCS;
    @Getter
    @Setter
    @Column(name = "agenda_disponible")
    @ElementCollection
    private List<Date> agendaDisp;
    @Getter
    @Setter
    @Column(name = "agenda_no_disponible")
    @ElementCollection
    private List<Date> agendaNoDisp;

    public Profesional() {
    }

    public Profesional(Long idPersona, String nombre, String apellido, Genero genero, String fechaNacimiento, Integer dni, String mail, String password, String phone, Long idProfesional, int matricula, Especialidad especialidad, Double reputacion, Double valorConsultaConCS, Double valorContulaSinCS, List<Date> agendaDisp, List<Date> agendaNoDisp) {
        super(idPersona, nombre, apellido, genero, fechaNacimiento, dni, mail, password, phone);
        this.idProfesional = idProfesional;
        this.matricula = matricula;
        this.especialidad = especialidad;
        this.reputacion = reputacion;
        this.valorConsultaConCS = valorConsultaConCS;
        this.valorContulaSinCS = valorContulaSinCS;
        this.agendaDisp = agendaDisp;
        this.agendaNoDisp = agendaNoDisp;
    }
}*/
