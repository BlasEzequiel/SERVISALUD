package com.EGG.ServiSalud.exceptions;

public class PacienteException extends Exception {

    public PacienteException() {
    }

    public PacienteException(String msg) {
        super(msg);
    }


}
//Creamos una clase expetions que herede los atributos del padre Exception  y le pasa al super el mensaje recibido por parametro
// Basicamente generamos etsa clase para diferenciar los errores que tengamos en la logica del negocio de los errores y backs
//Propios del sistema entonces nos llevamos PacienteException al PacienteServices que eestabamos editando