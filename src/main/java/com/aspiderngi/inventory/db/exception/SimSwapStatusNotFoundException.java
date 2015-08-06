package com.aspiderngi.inventory.db.exception;

public class SimSwapStatusNotFoundException extends Exception {
 
	private static final long serialVersionUID = -4542861573788524798L;

	public final String EXCEPTION_CODE = "SIMSWAP_STATUS_NOT_FOUND";
	
	public SimSwapStatusNotFoundException(){}
	
	public SimSwapStatusNotFoundException(String message){
		super(message);
	}

}
