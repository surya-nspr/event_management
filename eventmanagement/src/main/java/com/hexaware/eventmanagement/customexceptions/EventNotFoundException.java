package com.hexaware.eventmanagement.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException {
	private String eventName;
	private String fieldName;
	private Long feildValue;
	public EventNotFoundException(String eventName, String fieldName, Long feildValue) {
		super(String.format("%s not found with%s : %s", eventName,fieldName,feildValue));
		this.eventName = eventName;
		this.fieldName = fieldName;
		this.feildValue = feildValue;
	}
	
	
}
