package com.medical.drugService.exception;

public class InvalidIdException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidIdException(String msg) {
		super(msg);
	}
}
