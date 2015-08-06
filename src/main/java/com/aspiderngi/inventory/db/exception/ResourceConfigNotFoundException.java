package com.aspiderngi.inventory.db.exception;

public class ResourceConfigNotFoundException extends Exception {
 
	private static final long serialVersionUID = 1361331755911371163L;
	
	public final String EXCEPTION_CODE = "RESOURCE_NOT_FOUND";
	
	public ResourceConfigNotFoundException(){}
	
	public ResourceConfigNotFoundException(String message){
		super(message);
	}

}
