package com.EGG.ServiSalud.services;

import com.EGG.ServiSalud.Enums.*;
import com.EGG.ServiSalud.entities.Profesional;
import com.EGG.ServiSalud.entities.Turno;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesionalService implements UserDetailsService {
    @Autowired
    private ProfesionalPersistent perRepositorio;

    @Transactional
    public Profesional createProfesional(String nombre, String apellido, LocalDate fechaNacimiento,
                                         Genero genero, String mail, String password, String password2,
                                         String phone, Long dni, Integer matricula, Especialidad especialidad,
                                         Double valorConsulta, List<DiasDisponibles> diasDisponibles,
                                         String horarioInicio, String horarioSalida, String descripcion, MultipartFile file)
            throws ProfesionalException {
        try{
            validacionCrear(nombre, apellido, fechaNacimiento, genero,mail, password, password2, phone, dni,matricula, especialidad, valorConsulta,
                    diasDisponibles,horarioInicio, horarioSalida, descripcion, file);
            Profesional profesional = rellenarProfesional(nombre, apellido, fechaNacimiento, genero,mail, password, password2, phone, dni,matricula, especialidad, valorConsulta,
                     descripcion, file);
            perRepositorio.save(profesional);
            crearTurnos(horarioInicio, horarioSalida, diasDisponibles, profesional);
            return profesional;
        } catch (ProfesionalException ex) {
            throw new ProfesionalException(ex.getMessage());
        }
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

    private void validacionCrear(String nombre, String apellido, LocalDate fechaNacimiento,
                                 Genero genero, String mail, String password, String password2, String phone, Long dni,
                                 Integer matricula, Especialidad especialidad, Double valorConsulta,
                                 List<DiasDisponibles> diasDisponibles, String horarioInicio, String horarioSalida, String descripcion,
                                 MultipartFile file)
            throws ProfesionalException {
        Profesional profesional= new Profesional();
        if (nombre.isEmpty() || nombre == null) {
            throw new ProfesionalException("El nombre no puede estar vacío.");
        }
        if (apellido.isEmpty() || apellido == null) {//null significa que no hay espacio ocupado en la memoria
            throw new ProfesionalException("El apellido no puede estar vacío.");
        }
        if (fechaNacimiento == null) {
            throw new ProfesionalException("La fecha de nacimiento no puede estar vacía.");
        }
        if (genero == null) {
            throw new ProfesionalException("Debe seleccionar un género.");
        }
        if (phone == null) {
            throw new ProfesionalException("El campo teléfono es obligatorio.");
        }
        if (dni == null) {
            throw new ProfesionalException("El campo dni es obligatorio.");
        }
        if (matricula == null) {
            throw new ProfesionalException("El campo matricula es obligatorio.");
        }
        if (especialidad == null) {
            throw new ProfesionalException("El campo especialidad es obligatorio.");
        }
        if (valorConsulta == null) {
            throw new ProfesionalException("El campo valor consulta es obligatorio.");
        }
        if (diasDisponibles == null || diasDisponibles.isEmpty()) {
            throw new ProfesionalException("El campo días disponibles es obligatorio.");
        }
        if (horarioInicio == null || horarioInicio.isEmpty()) {
            throw new ProfesionalException("El campo horarios disponibles es obligatorio.");
        }
        if (horarioSalida == null || horarioSalida.isEmpty()) {
            throw new ProfesionalException("El campo horarios disponibles es obligatorio.");
        }
        if (descripcion.isEmpty() || descripcion == null) {
            throw new ProfesionalException("La descripcion no puede estar vacía.");
        }
        if(file.isEmpty() || file == null){
            throw new ProfesionalException("La imagen es obligatoria");
        } else {
            try{
                byte[] bytes = file.getBytes();
                Path path = Paths.get("static/img/" + file.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                throw new ProfesionalException("Archivo no permitido");
            }
        }
        //Validación de mail y contraseñas
        if (mail == null) {
            throw new ProfesionalException("El mail no puede estar vacío.");
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
        }
        //Fin Validación de mail y contraseñas
    } // Fin VALIDARDATOS

    private Profesional rellenarProfesional(String nombre, String apellido, LocalDate fechaNacimiento,
                                            Genero genero, String mail, String password, String password2, String phone, Long dni,
                                            Integer matricula, Especialidad especialidad, Double valorConsulta,
                                            String descripcion,
                                            MultipartFile file){
        Profesional profesional = new Profesional();
        profesional.setNombre(nombre);
        profesional.setApellido(apellido);
        profesional.setFechaNacimiento(fechaNacimiento);
        profesional.setGenero(genero);
        profesional.setPhone(phone);
        profesional.setDni(dni);
        profesional.setMatricula(matricula);
        profesional.setEspecialidad(especialidad);
        profesional.setValorConsulta(valorConsulta);
        profesional.setDescripcion(descripcion);
        profesional.setFoto(file.getOriginalFilename());
        profesional.setMail(mail);
        profesional.setRol(Rol.PROFESIONAL);
        profesional.setPassword(password);
        return profesional;
    }
    private List<Turno> crearTurnos(String horarioInicio, String horarioSalida, List<DiasDisponibles> diasDisponibles, Profesional profesional ){
        List<Turno> turnos = new ArrayList<>();

        List<Turno> turnosNuevos = new ArrayList<>();
        for(DiasDisponibles dia : diasDisponibles){
            DayOfWeek diaSemana = DayOfWeek.valueOf(dia.toString().toUpperCase());
            LocalTime horaTurno = LocalTime.parse(horarioInicio, DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime horaSalida = LocalTime.parse(horarioSalida, DateTimeFormatter.ofPattern("HH:mm"));
            while (horaTurno != horaSalida){
                LocalDateTime fechaTurno = LocalDateTime.now().with(diaSemana).with(horaTurno);
                Turno nuevoTurno = new Turno(profesional , fechaTurno, true);
                turnosNuevos.add(nuevoTurno);
                horaTurno = horaTurno.plusHours(1);
            }
        }
        for(Turno aux: turnosNuevos){
            turnos.add(aux);
            LocalDateTime fechaTurno2 = aux.getFechaYHorarioTurno().plusDays(7);
            Turno nuevoTurno = new Turno(profesional , fechaTurno2, true);
            turnos.add(nuevoTurno);
            LocalDateTime fechaTurno3 = aux.getFechaYHorarioTurno().plusDays(14);
            Turno nuevoTurno2 = new Turno(profesional , fechaTurno3, true);
            turnos.add(nuevoTurno2);
            LocalDateTime fechaTurno4 = aux.getFechaYHorarioTurno().plusDays(21);
            Turno nuevoTurno3 = new Turno(profesional , fechaTurno4, true);
            turnos.add(nuevoTurno3);

        }
        return turnos;
    }
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


