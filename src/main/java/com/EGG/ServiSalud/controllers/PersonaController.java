package com.EGG.ServiSalud.controllers;
import com.EGG.ServiSalud.entities.Enums.Genero;
import com.EGG.ServiSalud.entities.Persona;
import com.EGG.ServiSalud.exceptions.PersonaException;
import com.EGG.ServiSalud.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para operar
con la vista del usuario diseñada para la gestión de pacientes y profesionales (guardar/modificar, listar,
dar de baja, etc).*/
@Controller
@RequestMapping("/persona") //localhost:8080/ServiSalud/persona
public class PersonaController {
    @Autowired
    private final PersonaService perService;

    public PersonaController(PersonaService perService) {
        this.perService = perService;
    }

    @GetMapping("/registrar")
    public void crearPersona() throws PersonaException {
        perService.createPersona("Jeremias", "Rivelli", Genero.MASCULINO, "15/12/98", "chami@chami.com", 123456, "dasdas", "123456");
    }
    /*
    @GetMapping("/leerId")
    public Persona getPersona(Long id){
        return perService.getPersonaById(1l);
    }
    @GetMapping("/registrar") //localhost:8080/ServiSalud/persona/registro
    public String registrar(){
        return "formulario_prueba.html";
    }

     */

    /*@PostMapping("/registro")
    public String registro(@RequestParam String nombre, String apellido, Genero genero, Integer dni, String mail, String password, String phone){
        System.out.println("nombre:" + nombre);
        return "formulario_prueba.html";
    }
    //Obtener todas las personas
    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersonas(){
        List<Persona> personas = perService.getAllPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    //Obtener una persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id){
        Persona persona = perService.getPersonaById(id);
        if(persona != null){
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Crear una nueva persona
    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody String nombre, String apellido, Genero genero, String fechaNacimiento,
                                                 Integer dni, String mail, String password, String phone) throws PersonaException{
        if(nombre == null){
            throw new PersonaException("El nombre no puede ser nulo");
        }
        if(apellido == null){
            throw new PersonaException("El apellido no puede ser nulo");
        }
        if(genero == null){
            throw new PersonaException("El genero no puede ser nulo");
        }
        if(fechaNacimiento == null){
            throw new PersonaException("El fechaNacimiento no puede ser nulo");
        }
        if(dni == null){
            throw new PersonaException("El dni no puede ser nulo");
        }
        if(mail == null){
            throw new PersonaException("El mail no puede ser nulo");
        }
        if(password == null){
            throw new PersonaException("El password no puede ser nulo");
        }
        if(phone == null){
            throw new PersonaException("El phone no puede ser nulo");
        }
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setGenero(genero);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setDni(dni);
        persona.setMail(mail);
        persona.setPassword(password);
        persona.setPhone(phone);
        Persona createdPersona = perService.createPersona(persona);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }

    //Actualizar los datos de una persona
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails){
        Persona updatePersona = perService.updatePersona(id, personaDetails);
        if(updatePersona != null){
            return new ResponseEntity<>(updatePersona, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Eliminar una persona por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id){
        perService.deletePersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

     */
}
