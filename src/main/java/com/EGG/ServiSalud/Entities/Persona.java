package com.EGG.ServiSalud.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID aumentará de manera autoincremental
    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    private String phone;

    public Persona(Long id, String nombre, String apellido, String mail, String password, String phone) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
    }

}
