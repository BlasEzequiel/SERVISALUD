package com.EGG.ServiSalud.entities;
import com.EGG.ServiSalud.Enums.Rol;
import lombok.Getter;

@Getter
public class Admin {

    private final String mail = "admin@admin.com";
    private final String password = "admin";
    private Rol rol;
}

