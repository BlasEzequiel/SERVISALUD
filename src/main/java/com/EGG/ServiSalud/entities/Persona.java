package com.EGG.ServiSalud.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public abstract class Persona {

    @Getter@Setter@Column(name = "numero_de_identificación")@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Getter@Setter@Column(name = "nombre_completo")
    private String nombreCompleto;


    @Getter@Setter@Column(name = "genero")
    private Boolean genero;

    @Temporal(TemporalType.DATE) @Column(name = "fecha_de_nacimiento") @Getter @Setter
    private Date fechaNacimiento;

    @Getter@Setter@Column(name = "email")
    private String mail;

    @Getter@Setter@Column(name = "clave")
    private String password;

    @Getter@Setter@Column(name = "celular")
    private String phone;


    public Persona(String nombreCompleto, Boolean genero, Date fechaNacimiento, String mail, String password, String phone) {
        this.nombreCompleto = nombreCompleto;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
    }

    public Persona() {
    }
}
