package com.EGG.ServiSalud.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TurnoException extends Exception {
    public TurnoException(String msj) {
        super(msj);
    }

}
