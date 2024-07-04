package com.springboot.CustomeException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class EmptyInputFieldException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String statusCode;
	private String statusMessage;
	private String fieldName;
	public EmptyInputFieldException(String statusCode, String statusMessage, String fieldName) {
        super(String.format("Error: The '%s' field is empty. (Code: %s)", fieldName, statusCode));
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.fieldName = fieldName;
	}
	public EmptyInputFieldException(String statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	
	
}
