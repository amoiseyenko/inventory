package com.aspiderngi.inventory.db.exception;

public class CustomerDetailsNotFoundException extends Exception {
 
	 
	private static final long serialVersionUID = -4820517943593242492L;
	public final String EXCEPTION_CODE = "CUSTOMER_DETAILS_NOT_FOUND";
	
	public CustomerDetailsNotFoundException() {
	}
	
	public CustomerDetailsNotFoundException(String message){
		super(message);
	}
	
}
