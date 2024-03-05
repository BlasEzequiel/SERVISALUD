package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.entities.Enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "personas")
@ToString
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @Column(name = "idPersona")
    @Getter @Setter
    private Long idPersona;

    @Getter @Setter
    @Column(name = "nombre")
    private String nombre;

    @Getter @Setter
    @Column(name = "apellido")
    private String apellido;

    @Getter @Setter
    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Getter @Setter
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private String fechaNacimiento;

    @Getter @Setter
    @Column(name = "dni")
    private Integer dni;

    @Getter @Setter
    @Column(name = "mail")
    private String mail;

    @Getter @Setter
    @Column(name = "password")
    private String password;

    @Getter @Setter
    @Column(name = "phone")
    private String phone;

    public Persona() {
    }

    public Persona(String nombre, String apellido, Genero genero, String fechaNacimiento, Integer dni, String mail, String password, String phone) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
    }

}
