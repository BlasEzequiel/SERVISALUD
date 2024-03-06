package com.EGG.ServiSalud;
import com.EGG.ServiSalud.exceptions.PersonaException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiSaludApplication {

	public static void main(String[] args) throws PersonaException {
		SpringApplication.run(ServiSaludApplication.class, args);
	}

}
