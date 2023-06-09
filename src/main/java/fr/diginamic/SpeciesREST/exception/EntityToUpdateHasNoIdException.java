package fr.diginamic.SpeciesREST.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityToUpdateHasNoIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityToUpdateHasNoIdException() {
		super("Impossible de mettre à jour une entité sans un ID");
	}
}
