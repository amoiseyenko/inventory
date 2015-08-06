package com.aspiderngi.inventory.db.exception;

public class EmailAlreadyRegisteredException extends Exception {
 
	private static final long serialVersionUID = -8936145205573548320L;
	public  final String EXCEPTION_CODE = "EMAIL_ALREADY_REGISTERED";

	public EmailAlreadyRegisteredException() {}

    public EmailAlreadyRegisteredException(String message)
    {
       super(message);
    }
}

