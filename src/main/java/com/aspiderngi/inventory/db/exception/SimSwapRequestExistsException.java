package com.aspiderngi.inventory.db.exception;

public class SimSwapRequestExistsException extends Exception {
 
	 
	private static final long serialVersionUID = -6137814249153355896L;
	
	public final String EXCEPTION_CODE = "SIMSWAP_REQUEST_ALREADY_EXISTS";
	
	public SimSwapRequestExistsException(){}
	
	public SimSwapRequestExistsException(String message){
		super(message);
	}

}
