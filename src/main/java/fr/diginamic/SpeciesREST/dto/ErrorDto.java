package fr.diginamic.SpeciesREST.dto;

import java.time.LocalDateTime;

public class ErrorDto {
	private final int statusCode;
	private final LocalDateTime localDateTime;
	private final String message;
	private final String description;

	public ErrorDto(int statusCode, LocalDateTime localDateTime, String message, String description) {
		super();
		this.statusCode = statusCode;
		this.localDateTime = localDateTime;
		this.message = message;
		this.description = description;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

}
