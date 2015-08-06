package com.aspiderngi.inventory.db.exception;

public class ServerGenericException extends Exception{

	
	private static final long serialVersionUID = 6668321274023440658L;
	
	public final String EXCEPTION_CODE = "SERVICE_GENERIC_EXCEPTION";
	
	public ServerGenericException(){}
	
	public ServerGenericException(String message){
		super(message);
	}

}
