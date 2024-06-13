package com.hexaware.eventmanagement.customexceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hexaware.eventmanagement.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEventNotFoundException(EventNotFoundException ex,WebRequest w) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), w.getDescription(false),ex.getMessage(), "EVENT_NOT_FOUND");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEventDataException.class)
    public ResponseEntity<ErrorDetails> handleInvalidEventDataException(InvalidEventDataException ex,WebRequest w) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),w.getDescription(false), "INVALID_EVENT_DATA");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleNameAlreadyExistsException(NameAlreadyExistsException ex,WebRequest w) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),w.getDescription(false),  "EVENT_NAME_ALREADY_EXISTS");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
				Map<String,String> errors = new HashMap();
				
				List<ObjectError> errList=ex.getBindingResult().getAllErrors();
				errList.forEach((error)->{
					String fieldName = ((FieldError)error).getField();
					String message=error.getDefaultMessage();
					errors.put(fieldName,message);
				});
				
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}
	 @ExceptionHandler(AccessDeniedException.class)
	    public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException exception,
	                                                                        WebRequest webRequest){
	        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
	                webRequest.getDescription(false),"NOT_AUTHORIZED");
	        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	    }
}
