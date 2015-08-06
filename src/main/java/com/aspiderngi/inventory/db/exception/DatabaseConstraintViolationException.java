package com.aspiderngi.inventory.db.exception;

public class DatabaseConstraintViolationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1691951840457061511L;
	public final String EXCEPTION_CODE = "CONSTRAINT_VIOLATION";
	
	public DatabaseConstraintViolationException() {
	}
	
	public DatabaseConstraintViolationException(String message){
		super(message);
	}
}
