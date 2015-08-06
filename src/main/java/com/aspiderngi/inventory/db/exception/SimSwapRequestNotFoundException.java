package com.aspiderngi.inventory.db.exception;

public class SimSwapRequestNotFoundException extends Exception {
 
	private static final long serialVersionUID = 285276852808848827L;

	public final String EXCEPTION_CODE = "SIMSWAP_REQUEST_NOT_FOUND";
	
	public SimSwapRequestNotFoundException(){}
	
	public SimSwapRequestNotFoundException(String message){
		super(message);
	}

}