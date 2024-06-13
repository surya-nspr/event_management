package com.hexaware.eventmanagement.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEventDataException extends RuntimeException {
	private String message;
    public InvalidEventDataException(String message) {
        super(message);
    }
}
