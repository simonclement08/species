package fr.diginamic.SpeciesREST.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import fr.diginamic.SpeciesREST.dto.ErrorDto;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorDto handleExceptionNotFound(Exception exception, WebRequest request) {
		exception.printStackTrace();
		return new ErrorDto(
			HttpStatus.NOT_FOUND.value(),
			LocalDateTime.now(),
			exception.getMessage(),
			request.getDescription(false)
		);
	}
	
	@ExceptionHandler({
        EntityToCreateHasAnIdException.class,
        EntityToUpdateHasNoIdException.class
	})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorDto handleExceptionEntity(Exception exception, WebRequest request) {
		exception.printStackTrace();
		return new ErrorDto(
			HttpStatus.BAD_REQUEST.value(),
			LocalDateTime.now(),
			exception.getMessage(),
			request.getDescription(false)
		);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationException(MethodArgumentNotValidException exception, WebRequest request) {
        BindingResult bindingResult = exception.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String fieldName = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            errorMessage.append(fieldName).append(": ").append(defaultMessage).append("; ");
        }

		exception.printStackTrace();
        return new ErrorDto(
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now(),
            errorMessage.toString(),
			request.getDescription(false)
        );
    }
}
