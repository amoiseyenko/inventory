package com.aspiderngi.inventory.db.exception;

public class CustomerNotesNotFoundException extends Exception {
 
	private static final long serialVersionUID = -986441555275465443L;
	
	
	public final String EXCEPTION_CODE = "CUSTOMER_NOTE_NOT_FOUND";
	
	public CustomerNotesNotFoundException() {
	}
	
	public CustomerNotesNotFoundException(String message){
		super(message);
	}
	
}
