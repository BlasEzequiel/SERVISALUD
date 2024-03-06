package com.EGG.ServiSalud.entities.Enums;

public enum HorarioCita {
    OCHO_CERO("8:00"),
    OCHO_TREINTA("8:30"),
    NUEVE_CERO("9:00"),
    NUEVE_TREINTA("9:30"),
    DIEZ_CERO("10:00"),
    DIEZ_TREINTA("10:30"),
    ONCE_CERO("11:00"),
    ONCE_TREINTA("11:30"),
    DOCE_CERO("12:00");

    private String hora;

    HorarioCita(String hora){
        this.hora = hora;
    }

    public String getHora(){
        return hora;
    }

}
