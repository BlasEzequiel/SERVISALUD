package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.Enums.DiasDisponibles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FechaYHorarioTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_fechayhorario")
    private Long idFechayhorario;
    private DiasDisponibles diaDisponibles;
    private String horario;

}
