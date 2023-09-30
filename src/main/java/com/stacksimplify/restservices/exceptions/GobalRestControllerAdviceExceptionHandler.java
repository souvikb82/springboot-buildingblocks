package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GobalRestControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public CustomErrorDetails usernameNotFound(UserNameNotFoundException ex) {
		return new CustomErrorDetails(new Date(), "@RestControllerAdvice", ex.getMessage());
	}
}
