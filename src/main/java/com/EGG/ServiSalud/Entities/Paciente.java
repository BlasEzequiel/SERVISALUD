package com.EGG.ServiSalud.Entities;

import java.lang.reflect.Array;
import java.util.Date;

public class Paciente extends Persona{
    private boolean coberturaSocial;
    private String motivoDeConsulta;
    private Date fechaNacimiento;
    private Array[] historiaClinica;
    private String fotoPerfil;

    public Paciente(Long id, String nombre, String apellido, String mail, String password, String phone) {
        super(id, nombre, apellido, mail, password, phone);
    }


}
