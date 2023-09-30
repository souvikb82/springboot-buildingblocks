package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

//@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		CustomErrorDetails errorDetails = new CustomErrorDetails(new Date(), 
				"From MethodArgumentNotValidException in GEH", ex.getMessage());
		
		// TODO Auto-generated method stub
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		CustomErrorDetails errDetails = new CustomErrorDetails(new Date(), "From HttpRequestMethodNotSupportedExceptionHttpRequestMethodNotSupportedException in GEH", 
				ex.getMessage());
		
		return new ResponseEntity<>(errDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UserNameNotFoundException.class)
	public ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request){
		CustomErrorDetails errDetails = new CustomErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
		
		return new ResponseEntity<>(errDetails, HttpStatus.BAD_REQUEST);
	}
	
	//ConstraintViolationException
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
		CustomErrorDetails errDetails = new CustomErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
		
		return new ResponseEntity<>(errDetails, HttpStatus.BAD_REQUEST);
	}
}
