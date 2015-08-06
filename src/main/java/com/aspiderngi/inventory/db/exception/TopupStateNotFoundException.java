package com.aspiderngi.inventory.db.exception;

public class TopupStateNotFoundException extends Exception {

 
	private static final long serialVersionUID = -1668910421110134149L;
 
	public static final String EXCEPTION_CODE = "TOPUP_STATE_NOT_FOUND";

	public TopupStateNotFoundException() {}

    public TopupStateNotFoundException(String message) {
       super(message);
    }
}