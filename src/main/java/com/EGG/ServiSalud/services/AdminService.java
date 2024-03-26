package com.EGG.ServiSalud.services;
import com.EGG.ServiSalud.Enums.Rol;
import com.EGG.ServiSalud.entities.Admin;
import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.exceptions.AdminException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService implements UserDetailsService {


    public void iniciarAdmin(String mail, String password) throws AdminException {
        Admin admin = new Admin();
        if(!admin.getMail().equals(mail)){
            throw new AdminException("El mail ingresado no es válido");
        }
        if(!admin.getPassword().equals(password)){
            throw new AdminException("La contraseña ingresada no es válida");
        }
    }

    public void validarRol(Rol rol) throws AdminException {
        if(rol == null || rol.name().isEmpty()){
            throw new AdminException("El rol es obligatorio");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = new Admin();
        if (admin != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + admin.getRol().toString());
            permisos.add(p);
            return new User(admin.getMail(), admin.getPassword(), permisos);
        } else {
            return null;
        }
    }
}
