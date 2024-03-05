package com.EGG.ServiSalud;
import com.EGG.ServiSalud.entities.Enums.Genero;
import com.EGG.ServiSalud.entities.Persona;
import com.EGG.ServiSalud.exceptions.PersonaException;
import com.EGG.ServiSalud.persistent.PersonaPersistent;
import com.EGG.ServiSalud.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiSaludApplication {

	public static void main(String[] args) throws PersonaException {
		SpringApplication.run(ServiSaludApplication.class, args);
	}

}
