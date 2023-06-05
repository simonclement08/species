package fr.diginamic.SpeciesREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpeciesRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeciesRestApplication.class, args);
	}

}
