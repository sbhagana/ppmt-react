package com.ppmtoolfullstack.ppmt.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public final ResponseEntity<Object> projectIdExceptionHandler(ProjectIdException ex, WebRequest request) {
		ProjectIdExceptionResponse projectIdExceptionresponse = new ProjectIdExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(projectIdExceptionresponse, HttpStatus.BAD_REQUEST);
	}

	
	/*@ExceptionHandler(Exception.class)
	public final String CustomExceptionHandler(HttpServletRequest req , Exception ex) {
		return "This is a custom exception";
	}*/
	
	
	@ExceptionHandler
	public final ResponseEntity<Object> usernameAlreadyExistsHandler(UsernameAlreadyExistsException ex, WebRequest request) {
		UsernameAlreadyExistsResponse usernameAlreadyExistsException = new UsernameAlreadyExistsResponse(ex.getMessage());
		return new ResponseEntity<Object>(usernameAlreadyExistsException, HttpStatus.BAD_REQUEST);
	}

}
