package com.springboot.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.CustomeException.BusinessException;
import com.springboot.CustomeException.EmptyInputFieldException;
import com.springboot.payload.ApiResponce;

@ControllerAdvice
public class ControllorException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EmptyInputFieldException.class)
	public ResponseEntity<ApiResponce> handleEmptyException(EmptyInputFieldException emptyInputFieldException){
		String message =  emptyInputFieldException.getMessage();
		ApiResponce apiResponce = new ApiResponce(message, false);
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponce> handleEmptyException(BusinessException businessException){
		String message = businessException.getMessage();
		ApiResponce apiResponce = new ApiResponce(message, false);
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.BAD_REQUEST);
	}
}
