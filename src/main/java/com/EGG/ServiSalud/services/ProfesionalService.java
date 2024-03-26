package com.EGG.ServiSalud.services;

import com.EGG.ServiSalud.Enums.*;
import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.exceptions.PacienteException;
import com.EGG.ServiSalud.exceptions.ProfesionalException;
import com.EGG.ServiSalud.persistent.ProfesionalPersistent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesionalService implements UserDetailsService {
    @Autowired
    private ProfesionalPersistent perRepositorio;

    @Transactional
    public Profesional createProfesional(String nombre, String apellido, LocalDate fechaNacimiento,
                                         Genero genero, String mail, String password, String password2, String phone, Long dni,
                                         Integer matricula, Especialidad especialidad, Double valorConsulta, List<DiasDisponibles> diasDisponibles,
                                         List<LocalTime> horariosDisponibles, String descripcion) throws ProfesionalException {
        Profesional profesional = validacionCrear(nombre, apellido, fechaNacimiento, genero,mail, password, password2, phone, dni,matricula, especialidad, valorConsulta,
                diasDisponibles,horariosDisponibles, descripcion);
        return perRepositorio.save(profesional);
    }

    public Optional<Profesional> buscarPorId(Long idProfesional) {
        return perRepositorio.findById(idProfesional);
    }

    public List<Profesional> buscarPorEspecialidad(Especialidad especialidad){
        return perRepositorio.buscarPorEspecialidad(especialidad);
    }

    public List<Profesional> listarProfesionales() {
        return perRepositorio.findAll();
    }

    public Profesional validarInicioDeSesion(String email, String password) throws ProfesionalException {
        if(email.isEmpty() || email == null){
            throw new ProfesionalException("Todos los campos son obligatorios.");
        }else{
            Optional<Profesional> optional= perRepositorio.buscarPorMailOptional(email);
            if (optional.isPresent()){
                Profesional profesional = optional.get();
                if (!profesional.getPassword().equals(password)){
                    throw new ProfesionalException("La contraseña ingresada no es correcta.");
                }
                return profesional;

            } else {
                throw new ProfesionalException("El mail ingresado no es correcto.");
            }
        }
    }
    @Transactional
    public void deletePersona(Long id) {
        perRepositorio.deleteById(id);

    }

    private Profesional validacionCrear(String nombre, String apellido, LocalDate fechaNacimiento,
                                        Genero genero, String mail, String password, String password2, String phone, Long dni,
                                        Integer matricula, Especialidad especialidad, Double valorConsulta,
                                        List<DiasDisponibles> diasDisponibles, List<LocalTime> horariosDisponibles, String descripcion)
            throws ProfesionalException {
        Profesional profesional= new Profesional();
        if (nombre.isEmpty() || nombre == null) {
            throw new ProfesionalException("El nombre no puede estar vacío.");
        } else {
            profesional.setNombre(nombre);
        }

        if (apellido.isEmpty() || apellido == null) {//null significa que no hay espacio ocupado en la memoria
            throw new ProfesionalException("El apellido no puede estar vacío.");
        } else {
            profesional.setApellido(apellido);
        }

        if (fechaNacimiento == null) {
            throw new ProfesionalException("La fecha de nacimiento no puede estar vacía.");
        }else {
            profesional.setFechaNacimiento(fechaNacimiento);
        }
        if (genero == null) {
            throw new ProfesionalException("Debe seleccionar un género.");
        }else {
            profesional.setGenero(genero);
        }
        if (phone == null) {
            throw new ProfesionalException("El campo teléfono es obligatorio.");
        }else {
            profesional.setPhone(phone);
        }

        if (dni == null) {
            throw new ProfesionalException("El campo dni es obligatorio.");
        }else {
            profesional.setDni(dni);
        }
        if (matricula == null) {
            throw new ProfesionalException("El campo matricula es obligatorio.");
        }else {
            profesional.setMatricula(matricula);
        }
        if (especialidad == null) {
            throw new ProfesionalException("El campo especialidad es obligatorio.");
        }else {
            profesional.setEspecialidad(especialidad);
        }
        if (valorConsulta == null) {
            throw new ProfesionalException("El campo valor consulta es obligatorio.");
        }else {
            profesional.setValorConsulta(valorConsulta);
        }
        if (diasDisponibles == null || diasDisponibles.isEmpty()) {
            throw new ProfesionalException("El campo días disponibles es obligatorio.");
        }else {
            profesional.setDiasDisponibles(diasDisponibles);
        }
        if (horariosDisponibles == null || horariosDisponibles.isEmpty()) {
            throw new ProfesionalException("El campo horarios disponibles es obligatorio.");
        }else {
            profesional.setHorariosDisponibles(horariosDisponibles);
        }
        if (descripcion.isEmpty() || descripcion == null) {
            throw new ProfesionalException("La descripcion no puede estar vacía.");
        } else {
            profesional.setDescripcion(descripcion);
        }

        //Validación de mail y contraseñas

        if (mail == null) {
            throw new ProfesionalException("El mail no puede estar vacío.");
        }else {
            profesional.setMail(mail);
        }
        Optional<Profesional> optional = perRepositorio.buscarPorMailOptional(mail);
        if(optional.isPresent()) {
            throw new ProfesionalException("El mail ingresado ya existe");
        }
        if (password == null || password.length() <= 5) {
            throw new ProfesionalException("El password no puede estar vacío y debe tener 5 dígitos o más.");
        }
        if (!password.equals(password2)) {
            throw new ProfesionalException("Las contraseñas ingresadas deben ser iguales.");
        } else {
            profesional.setPassword(password);
        }
        //Fin Validación de mail y contraseñas
        // profesional.setImagen(imagen);
        profesional.setRol(Rol.PROFESIONAL);

        return profesional;
    } // Fin VALIDARDATOS

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Profesional profesional = perRepositorio.buscarPorMail(email);
        if (profesional != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + profesional.getRol().toString());
            permisos.add(p);
            return new User(profesional.getMail(), profesional.getPassword(), permisos);
        } else {
            return null;
        }
    }
}

