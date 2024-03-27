package com.EGG.ServiSalud.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@Getter @Setter
@Entity
@Table(name = "turnos")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_turno")
    private Long idTurno;
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "id_profesional")
    private Profesional profesional;
    private Integer calificacion;
    @Column(name = "fechayhorario")
    private LocalDateTime fechaYHorarioTurno;
    private Boolean realizado;
    private Boolean disponible;

    public Turno(Profesional profesional, LocalDateTime fechaYHorarioTurno, Boolean disponible) {
        this.profesional = profesional;
        this.fechaYHorarioTurno = fechaYHorarioTurno;
        this.disponible = disponible;
    }
}
