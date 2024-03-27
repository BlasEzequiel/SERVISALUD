package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.Enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persona {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPersona;
    private Long dni;
    private String nombre;
    private String apellido;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_de_nacimiento")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Column(name="mail", nullable = false, unique = true)
    private String mail;
    private String password;
    private String phone;
    @OneToMany
    @JoinColumn(name = "id_turno")
    private List<Turno> listaTurnos;
    public Persona() {
    }
}
