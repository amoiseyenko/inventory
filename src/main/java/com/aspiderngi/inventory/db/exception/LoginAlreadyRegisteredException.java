package com.aspiderngi.inventory.db.exception;

public class LoginAlreadyRegisteredException extends Exception {
  
	private static final long serialVersionUID = 8113185868751297241L;
	public  final String EXCEPTION_CODE = "LOGIN_ALREADY_REGISTERED";

	public LoginAlreadyRegisteredException() {}

    public LoginAlreadyRegisteredException(String message)
    {
       super(message);
    }
}

