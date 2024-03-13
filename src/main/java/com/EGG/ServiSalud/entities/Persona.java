package com.EGG.ServiSalud.entities;

import com.EGG.ServiSalud.entities.Enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persona {

    @Getter@Setter@Column(name = "numero_de_identificación")@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Getter @Setter @Column(name = "numero_de_identifiacion_nacional")
    private Long dni;

    @Getter@Setter@Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;


    @Getter@Setter@Column(name = "genero")
    private String genero;

    @Temporal(TemporalType.DATE) @Column(name = "fecha_de_nacimiento") @Getter @Setter
    private Date fechaNacimiento;

    @Getter@Setter@Column(name = "email")
    private String mail;

    @Getter@Setter@Column(name = "clave")
    private String password;

    @Getter@Setter@Column(name = "celular")
    private String phone;


    public Persona(String nombre, String apellido, String genero, Date fechaNacimiento, String mail,
                   String password, String phone) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
    }

    public Persona() {
    }
}
