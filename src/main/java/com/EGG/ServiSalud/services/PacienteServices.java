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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServices implements UserDetailsService {
    @Autowired
    //indica al servidor de aplicaciones que esta variable va a ser inicializada por él, esto es lo que se conoce como inyección de dependencias
    private PacientePersistent PacienteRepository; //Generamos una instancia del PacienteRepository, Metodo create para settear los datos del paciente y crearlos en la base de datos

    //Establece que si el metodo se ejecuta sin lanzar excepciones se realiza un commit a la base de datos y
    // se aplican los cambios, encambio si el metodo lanza una excepcion y no es atrapada se vuelve a atras con la
    // transaccions, es decir se hace un rollback y no se aplica nada en la base de datos, por lo tanto podemos decir que todos
    // aquellos metodos que generen modificaciones permanentes en la base de datos debe ser anotados como transacionales, por ejemplos
    // Los metodos listar solo son de consulta entonces no generan cambios en la base de datos, pero los metodos create, update y delete si
    @Transactional
    public void CrearPaciente(String nombre, String apellido, CoberturaMedica coberturaMedica, String fechaNacimiento,
                              Genero genero, String mail, String password, String password2, String phone, Long dni) throws PacienteException, ParseException {
        validacionCrear(nombre, apellido, fechaNacimiento, genero, coberturaMedica, mail, password, password2, phone, dni);//Si no se pasa las excepciones por no completar los datos se lanza la excepción y no se va a ejecutar el codigo debajo de la validación que hicimos //Por lo tanto no se va a persistir
        Paciente paciente = new Paciente();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        paciente.setNombre(nombre);
        paciente.setDni(dni);
        paciente.setApellido(apellido);
        paciente.setFechaNacimiento(sdf.parse(fechaNacimiento));//cambiamos fechaNacimiento y ponemos new Date() para que se instancie con la fecha del momento en que el objeto se crea
        paciente.setGenero(genero); //Con el usuarioPaciente creado debemos llamar el repositorio para persistir este objeto,
        paciente.setMail(mail);
        paciente.setPassword(password);
        paciente.setPhone(phone);
        paciente.setCoberturaMedica(coberturaMedica);
        paciente.setRol(Rol.PACIENTE);
        // paciente.setHistoriaClinica(historiaClinica);
        // paciente.setImagen(imagen);

        // con todos los anteriores atributos completos, llamamos al metodo save para que lo llame y lo persista en la base de datos
        PacienteRepository.save(paciente); //Este metodo save recibe una entidad por parametro y la guarda, la persiste en la base de datos
    }

    //Metodo Read
    public List<Paciente> ListarPacientes() {//Este metodo no recibe parametros
        return PacienteRepository.findAll();
    }

    public Paciente buscarPacientePorEmail(String email) {
        return PacienteRepository.buscarPorEmail(email);
    }
    //
    public Paciente validarInicioSesion(String email, String password) throws PacienteException {
        Paciente paciente = PacienteRepository.buscarPorEmail(email);
        if(email.isEmpty() || email == null){
            throw new PacienteException("Todos los campos son obligatorios.");
        }
        if (paciente == null) {
            throw new PacienteException("El mail ingresado no es correcto");
        }
        if (!paciente.getPassword().equals(password)) {
            throw new PacienteException("La contraseña ingresada no es correcta.");
        }
        return paciente;
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return PacienteRepository.findById(id);
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
    private void validacionCrear(String nombre, String apellido, String fechaNacimiento, Genero genero, CoberturaMedica coberturaMedica,
                                 String mail, String password, String password2, String phone, Long dni) throws PacienteException {
        if (nombre.isEmpty() || nombre == null) {
            throw new PacienteException("El nombre no puede estar vacío.");
        }
        if (apellido.isEmpty() || apellido == null) {//null significa que no hay espacio ocupado en la memoria
            throw new PacienteException("El apellido no puede estar vacío.");
        }
        if (fechaNacimiento == null || fechaNacimiento.isEmpty()) {
            throw new PacienteException("La fecha de nacimiento no puede estar vacía.");
        }
        if (genero == null) {
            throw new PacienteException("Debe seleccionar un género.");
        }
        if (coberturaMedica == null) {
            throw new PacienteException("La cobertura medica no puede estar vacía.");
        }
        if (mail == null) {
            throw new PacienteException("El mail no puede estar vacío.");
        }
        if (password == null || password.length() <= 5) {
            throw new PacienteException("El password no puede estar vacío y debe tener 5 dígitos o más.");
        }
        if (!password.equals(password2)) {
            throw new PacienteException("Las contraseñas ingresadas deben ser iguales.");
        }
        if (phone == null) {
            throw new PacienteException("El campo teléfono es obligatorio.");
        }
        if (dni == null) {
            throw new PacienteException("El campo dni es obligatorio.");
        }
    } // Fin VALIDARDATOS
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Paciente paciente = PacienteRepository.buscarPorEmail(email);
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
