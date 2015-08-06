package com.aspiderngi.inventory.db.exception;

public class PasswordWeakException extends Exception {
 
	private static final long serialVersionUID = 5959905575810190875L;
	public  final String EXCEPTION_CODE = "PASSWORD_IS_WEAK";

	public PasswordWeakException() {}

    public PasswordWeakException(String message)
    {
       super(message);
    }
}

