package fr.diginamic.SpeciesREST.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityToCreateHasAnIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityToCreateHasAnIdException() {
		super("Impossible de créer une entité avec un ID");
	}
}
