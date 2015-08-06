package com.aspiderngi.inventory.db.exception;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = -4468852428887567555L;
	
	public  final String EXCEPTION_CODE = "CUSTOMER_NOT_FOUND";

	public CustomerNotFoundException() {}

    public CustomerNotFoundException(String message)
    {
       super(message);
    }
}

