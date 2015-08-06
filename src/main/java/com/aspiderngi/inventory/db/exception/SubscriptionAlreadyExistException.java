package com.aspiderngi.inventory.db.exception;

public class SubscriptionAlreadyExistException extends Exception {

	private static final long serialVersionUID = -8951476528067574303L;
	
	public final String EXCEPTION_CODE = "SUBSCRIPTION_EXIST";

	public SubscriptionAlreadyExistException() {}

    public SubscriptionAlreadyExistException(String message)
    {
       super(message);
    }
}