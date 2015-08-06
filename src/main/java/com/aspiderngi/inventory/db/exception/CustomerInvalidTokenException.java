package com.aspiderngi.inventory.db.exception;

public class CustomerInvalidTokenException extends Exception {

	private static final long serialVersionUID = -7629684871992833293L;
	
	public final String EXCEPTION_CODE = "NP_CUSTOMER_INVALID_TOKEN";

	public CustomerInvalidTokenException() {}

    public CustomerInvalidTokenException(String message)
    {
       super(message);
    }
}
