/*package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.entities.Enums.HorarioCita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "turnos")
@ToString
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idTurno")
    private Long idTurno;

    @ManyToOne
    @Getter @Setter @JoinColumn(name = "idPaciente")
    private Paciente paciente;

    @ManyToOne
    @Getter @Setter @JoinColumn(name = "idProfesional")
    private Profesional profesional;

    @Getter @Setter @Column(name = "calificacion")
    private Integer calificacion;

    @Getter @Setter @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Getter @Setter @Column(name = "horario")
    private Integer horarioCita;

    @Getter @Setter @Column(name = "realizado")
    private Boolean realizado;

    @Getter @Setter @Column(name = "disponible")
    private Boolean disponible;

    public Turno() {
    }

    public Turno(Profesional profesional, Date fecha, Integer horarioCita) {
        this.profesional = profesional;
        this.fecha = fecha;
        this.horarioCita = horarioCita;
        this.realizado = false;
        this.disponible = true;
    }
}
*/