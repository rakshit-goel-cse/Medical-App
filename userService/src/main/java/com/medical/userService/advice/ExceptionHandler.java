package com.medical.userService.advice;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.medical.userService.exception.InvalidIdException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<Object> invalidIdException(InvalidIdException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST); 
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNoElemetExcep(NoSuchElementException excp){
		return new ResponseEntity<Object>("No Data found kindly try with different request",HttpStatus.NO_CONTENT);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Media type wrong Kindly check the request",HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleSqlDataException(DataIntegrityViolationException exc){
		return new ResponseEntity<String>("Issue in SQL Query - "+exc.getMessage(),HttpStatus.CONFLICT);
	}
	
	
	
	
	
}
