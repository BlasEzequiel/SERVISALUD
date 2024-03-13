package com.EGG.ServiSalud.services;

import com.EGG.ServiSalud.entities.Paciente;
import com.EGG.ServiSalud.exceptions.PacienteException;
import com.EGG.ServiSalud.persistent.PacientePersistent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PacienteServices {
    @Autowired
    //indica al servidor de aplicaciones que esta variable va a ser inicializada por él, esto es lo que se conoce como inyección de dependencias
    private PacientePersistent PacienteRepository; //Generamos una instancia del PacienteRepository, Metodo create para settear los datos del paciente y crearlos en la base de datos


    @Transactional
    //Establece que si el metodo se ejecuta sin lanzar excepciones se realiza un commit a la base de datos y // se aplican los cambios, encambio si el metodo lanza una excepcion y no es atrapada se vuelve a atras con la // transaccions, es decir se hace un rollback y no se aplica nada en la base de datos, por lo tanto podemos decir que todos //aquellos metodos que generen modificaciones permanentes en la base de datos debe ser anotados como transacionales, por ejemplos //Los metodos listar solo son de consulta entonces no generan cambios en la base de datos, pero los metodos create, update y delete si

    public void CrearPaciente(String nombre, String apellido, Boolean coberturaMedica, Date fechaNacimiento, String genero,
                              String mail, String password, String phone, Long dni)
            throws PacienteException {
        validacionCrear(nombre, fechaNacimiento, genero, coberturaMedica, mail, password, phone, dni);//Si no se pasa las excepciones por no completar los datos se lanza la excepción y no se va a ejecutar el codigo debajo de la validación que hicimos //Por lo tanto no se va a persistir


        Paciente paciente = new Paciente();
        paciente.setNombre(nombre);
        paciente.setDni(dni);
        paciente.setApellido(apellido);
        paciente.setFechaNacimiento(fechaNacimiento);//cambiamos fechaNacimiento y ponemos new Date() para que se instancie con la fecha del momento en que el objeto se crea
        paciente.setGenero(genero); //Con el usuarioPaciente creado debemos llamar el repositorio para persistir este objeto,
        paciente.setMail(mail);
        paciente.setPassword(password);
        paciente.setPhone(phone);
        paciente.setCoberturaMedica(coberturaMedica);
        // paciente.setHistoriaClinica(historiaClinica);
       // paciente.setImagen(imagen);

        // con todos los anteriores atributos completos, llamamos al metodo save para que lo llame y lo persista en la base de datos
        PacienteRepository.save(paciente); //Este metodo save recibe una entidad por parametro y la guarda, la persiste en la base de datos
    }



    //Metodo Read
    public List<Paciente> ListarPacientes(){//Este metodo no recibe parametros
        return PacienteRepository.findAll();
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



    private void validacionCrear(String nombreCompleto, Date fechaNacimiento, String genero, Boolean coberturaMedica,
                                 String mail, String password, String phone, Long dni) throws PacienteException {
        if (nombreCompleto.isEmpty()) {//null significa que no hay espacio ocupado en la memoria
            throw new PacienteException("El nombre no puede estar vacio");
        }
        if (fechaNacimiento == null) {
            throw new PacienteException("La fecha de nacimiento no puede estar vacía");
        }

        if (genero.isEmpty()) {
            throw new PacienteException("El género no puede estar vacío");
        }
        if (coberturaMedica == null) {
            throw new PacienteException("El cobertura medica no puede estar vacío");
        }
        if (mail == null) {
            throw new PacienteException("El mail no puede estar vacío");
        }
        if (password == null) {
            throw new PacienteException("El password no puede estar vacío");
        }
        if (phone == null) {
            throw new PacienteException("El phone no puede estar vacío");
        }
        if (dni == null) {
            throw new PacienteException("El phone no puede estar vacío");
        }




    }
}
