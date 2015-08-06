package com.aspiderngi.inventory.db.exception;

public class MSISDNAlreadyRegisteredException extends Exception {
	 
	private static final long serialVersionUID = 7408967351811607238L;
	
	public final String EXCEPTION_CODE = "MSISDN_ALREADY_REGISTERED";

	public MSISDNAlreadyRegisteredException() {}

    public MSISDNAlreadyRegisteredException(String message)
    {
       super(message);
    }
}

