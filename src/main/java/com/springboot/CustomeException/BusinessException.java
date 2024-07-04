package com.springboot.CustomeException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String statusCode;
	private String statusMessage;
	private String fieldName;
	public BusinessException(String statusCode, String statusMessage, String fieldName) {
		super(String.format("Error in field '%s': %s (Code: %s)", fieldName, statusMessage, statusCode));
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.fieldName = fieldName;
	}
	
	
}
