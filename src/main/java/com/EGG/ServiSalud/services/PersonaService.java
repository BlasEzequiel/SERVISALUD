package com.EGG.ServiSalud.services;
import com.EGG.ServiSalud.entities.Enums.Genero;
import com.EGG.ServiSalud.entities.Persona;
import com.EGG.ServiSalud.exceptions.PersonaException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/*Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
administrar (consulta, creación, modificación y dar de baja).*/
@Service
public class PersonaService {
    @Autowired
    private PersonaPersistent perRepositorio;

    /* CREAR PERSONA
    * Este método recibe un Objeto de tipo Persona.
    * En el caso de que persona == null arrojará una PersonaException (dir = exceptions/PersonaException.
    * ¿Aquí se podría agregar una validación para corroborar que ninguno de los datos que rellenan a Persona esten vacios?
    * En el caso de que persona != null se utilizará el objeto de Persistencia creado, con el método save.
    * */
    @Transactional()
    public Persona createPersona(String nombre, String apellido, Genero genero,
                                 String fechaNacimiento,String mail, Integer dni, String password, String phone ) throws PersonaException {
        if (nombre == null) {
            throw new PersonaException("El nombre no puede estar vacío");
        }
        if (apellido == null) {
            throw new PersonaException("El apellido no puede estar vacío");
        }
        if (genero == null) {
            throw new PersonaException("El genero no puede estar vacío");
        }
        if (fechaNacimiento == null) {
            throw new PersonaException("El fechaNacimiento no puede estar vacío");
        }
        if (dni == null) {
            throw new PersonaException("El dni no puede estar vacío");
        }
        if (mail == null) {
            throw new PersonaException("El mail no puede estar vacío");
        }
        if (password == null) {
            throw new PersonaException("El password no puede estar vacío");
        }
        if (phone == null) {
            throw new PersonaException("El phone no puede estar vacío");
        }
        Persona persona = new Persona(nombre, apellido, genero, fechaNacimiento, dni, mail, password,phone);
        return perRepositorio.save(persona);
    }


    /*Obtener todas las personas*/
    public List<Persona> getAllPersonas(){
        return perRepositorio.findAll();
    }

    /*Obtener persona por ID*/
    public Persona getPersonaById(Long id){
        return perRepositorio.findById(id).get();
    }

    /*Actualizar los datos de una persona*/
    @Transactional()
    public Persona updatePersona(Long id, Persona personaDetails){
        Optional<Persona> respuesta = perRepositorio.findById(id);
        Persona per = new Persona();
        if(respuesta.isPresent()){
            per = personaDetails;
        }
        return per;
    }
    /*Eliminar una persona por su ID*/
    @Transactional()
    public void deletePersona(Long id){
        perRepositorio.deleteById(id);
    }
}

