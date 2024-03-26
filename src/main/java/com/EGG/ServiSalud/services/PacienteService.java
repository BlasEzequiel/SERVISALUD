package com.EGG.ServiSalud.services;
import com.EGG.ServiSalud.Enums.CoberturaMedica;
import com.EGG.ServiSalud.Enums.Genero;
import com.EGG.ServiSalud.Enums.Rol;
import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.exceptions.PacienteException;
import com.EGG.ServiSalud.persistent.PacientePersistent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements UserDetailsService {
    @Autowired
    private PacientePersistent pacientePersistent;

    @Transactional
    public void CrearPaciente(String nombre, String apellido, CoberturaMedica coberturaMedica, LocalDate fechaNacimiento,
                              Genero genero, String mail, String password, String password2, String phone, Long dni) throws PacienteException, ParseException {
        Paciente paciente = validacionCrear(nombre, apellido, fechaNacimiento, genero, coberturaMedica, mail, password, password2, phone, dni);
        pacientePersistent.save(paciente);
    }

    //Metodo Read
    public Paciente buscarPacientePorEmail(String email) {
        return pacientePersistent.buscarPorEmail(email);
    }
    //
    public Paciente validarInicioSesion(String email, String password) throws PacienteException {
        if(email.isEmpty() || email == null){
            throw new PacienteException("Todos los campos son obligatorios.");
        }else{
            Optional<Paciente> optional = pacientePersistent.buscarPorEmailOptional(email);
            if (optional.isPresent()){
                Paciente paciente = optional.get();
                if (!paciente.getPassword().equals(password)) {
                    throw new PacienteException("La contraseña ingresada no es correcta.");
                }
                return paciente;

            }else{
                throw new PacienteException("El mail ingresado no es correcto");

            }
        }
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacientePersistent.findById(id);
    }

    /*
    public void UpdatePaciente(Long idPaciente, String nombreCompleto, Boolean coberturaMedica, Date fechaNacimiento, Boolean genero, String mail, String password, String phone){

        Paciente RespuestaPaciente = PacienteRepository.findById(idPaciente).get();

        if(RespuestaPaciente.isPresent()){
            Paciente paciente = RespuestaPaciente.get(); //Esto nos trae los atributos del paciente// Una vez los atributos esten podemos settiar los atributos del paciente
            paciente.setNombreCompleto(nombreCompleto);
            paciente.setFechaNacimiento(fechaNacimiento);
            paciente.setCoberturaMedica();
            paciente.setGenero(genero);
            paciente.setMotivoDeConsulta(motivoDeConsulta);
            PacienteRepository.save((paciente));
        }
    }

    @Transactional
    public void deletePaciente(Long idPaciente) throws PacienteException {
        if (idPaciente == null) {
            throw new PacienteException("El id del paciente no puede estar vacío");
        }

        Optional<Paciente> pacienteOptional = PacienteRepository.findById(idPaciente);

        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            PacienteRepository.delete(paciente);
        } else {
            throw new PacienteException("No se encuentra un paciente con el id " + idPaciente);
        }
    }


*/
    private Paciente validacionCrear(String nombre, String apellido, LocalDate fechaNacimiento, Genero genero, CoberturaMedica coberturaMedica,
                                     String mail, String password, String password2, String phone, Long dni) throws PacienteException {
        Paciente paciente = new Paciente();

        if (nombre.isEmpty() || nombre == null) {
            throw new PacienteException("El nombre no puede estar vacío.");
        } else {
            paciente.setNombre(nombre);
        }

        if (apellido.isEmpty() || apellido == null) {//null significa que no hay espacio ocupado en la memoria
            throw new PacienteException("El apellido no puede estar vacío.");
        } else {
            paciente.setApellido(apellido);
        }

        if (fechaNacimiento == null) {
            throw new PacienteException("La fecha de nacimiento no puede estar vacía.");
        }else {
            paciente.setFechaNacimiento(fechaNacimiento);
        }

        if (genero == null) {
            throw new PacienteException("Debe seleccionar un género.");
        }else {
            paciente.setGenero(genero);
        }

        if (coberturaMedica == null) {
            throw new PacienteException("La cobertura medica no puede estar vacía.");
        }else {
            paciente.setCoberturaMedica(coberturaMedica);
        }

        if (phone == null) {
            throw new PacienteException("El campo teléfono es obligatorio.");
        }else {
            paciente.setPhone(phone);
        }

        if (dni == null) {
            throw new PacienteException("El campo dni es obligatorio.");
        }else {
            paciente.setDni(dni);
        }

        //Validación de mail y contraseñas

        if (mail == null) {
            throw new PacienteException("El mail no puede estar vacío.");
        }else {
            paciente.setMail(mail);
        }
        Optional<Paciente> optional = pacientePersistent.buscarPorEmailOptional(mail);
        if(optional.isPresent()) {
            throw new PacienteException("El mail ingresado ya existe");
        }
        if (password == null || password.length() <= 5) {
            throw new PacienteException("El password no puede estar vacío y debe tener 5 dígitos o más.");
        }
        if (!password.equals(password2)) {
            throw new PacienteException("Las contraseñas ingresadas deben ser iguales.");
        } else {
            paciente.setPassword(password);
        }
        //Fin Validación de mail y contraseñas

        // paciente.setHistoriaClinica(historiaClinica);
        paciente.setRol(Rol.PACIENTE);

        return paciente;
    } // Fin VALIDARDATOS

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Paciente paciente = pacientePersistent.buscarPorEmail(email);
        if (paciente != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + paciente.getRol().toString());
            permisos.add(p);
            return new User(paciente.getMail(), paciente.getPassword(), permisos);
        } else {
            return null;
        }
    }
}
