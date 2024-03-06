package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.entities.Enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public abstract class Persona {
    private Long idPersona;

    private String nombre;

    private String apellido;

    private Genero genero;

    private String fechaNacimiento;

    private Integer dni;

    private String mail;

    private String password;

    private String phone;

    public Persona() {
    }

    public Persona(Long idPersona, String nombre, String apellido, Genero genero, String fechaNacimiento, Integer dni, String mail, String password, String phone) {
        this.idPersona = idPersona;
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
