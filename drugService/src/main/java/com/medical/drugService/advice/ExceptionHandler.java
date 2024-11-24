package com.medical.drugService.advice;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.medical.drugService.exception.InvalidIdException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	@org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> dataNotFound(NoSuchElementException exp){
		return new ResponseEntity<Object>("Data not found",HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<Object> invalidId(InvalidIdException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
	public ResponseEntity<Object> sqlExp(SQLException exp){
		return new ResponseEntity<Object>(exp.getMessage(),HttpStatus.EXPECTATION_FAILED);
	} 
}
