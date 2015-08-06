package com.aspiderngi.inventory.db.exception;

public class CustomerStateNotFoundException extends Exception {

	private static final long serialVersionUID = 6647544141197448266L;
	public final String EXCEPTION_CODE = "CUSTOMER_STATE_NOT_FOUND";

	public CustomerStateNotFoundException() {}

    public CustomerStateNotFoundException(String message) {
       super(message);
    }
}