package com.EGG.ServiSalud.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProfesionalException extends Exception {
    public ProfesionalException(String msj) {
        super(msj);
    }
}
