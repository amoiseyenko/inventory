package com.aspiderngi.inventory.db.exception;

public class CustomerInvalidStateException extends Exception {
	
	private static final long serialVersionUID = -4057399963216857237L;
	
	public final String EXCEPTION_CODE = "NP_CUSTOMER_INVALID_STATE";

	public CustomerInvalidStateException() {}

    public CustomerInvalidStateException(String message)
    {
       super(message);
    }
}

