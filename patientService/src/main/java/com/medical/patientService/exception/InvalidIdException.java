package com.medical.patientService.exception;

public class InvalidIdException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidIdException() {
		super();
	}
	
	public InvalidIdException(String msg) {
		super(msg);
	}

	public InvalidIdException(String msg,Throwable th) {
		super(msg,th);
	}
	
	public InvalidIdException(Throwable th) {
		super(th);
	}
}
