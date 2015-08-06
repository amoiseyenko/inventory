package com.aspiderngi.inventory.db.exception;

public class CustomerPatchIllegalArguments extends Exception {

	private static final long serialVersionUID = 1L;
	
	public final String EXCEPTION_CODE = "ILLEGAL_ARGUMENTS";
	
	public CustomerPatchIllegalArguments() {
	}
	
	public CustomerPatchIllegalArguments(String message){
		super(message);
	}
	
	
}
