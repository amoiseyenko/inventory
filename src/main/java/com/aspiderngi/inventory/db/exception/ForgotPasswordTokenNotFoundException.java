package com.aspiderngi.inventory.db.exception;

public class ForgotPasswordTokenNotFoundException extends Exception {
	
	private static final long serialVersionUID = -7771811325052032608L;
	public final String EXCEPTION_CODE = "FORGOT_PASSWORD_TOKEN_NOT_FOUND";
	
	public ForgotPasswordTokenNotFoundException() {
	}
	
	public ForgotPasswordTokenNotFoundException(String message){
		super(message);
	}
}
