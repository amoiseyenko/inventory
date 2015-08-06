package com.aspiderngi.inventory.db.exception;

public class SubscriptionStateNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8751792653654445172L;
	public static final String EXCEPTION_CODE = "SUBSCRIPTION_STATE_NOT_FOUND";

	public SubscriptionStateNotFoundException() {}

    public SubscriptionStateNotFoundException(String message) {
       super(message);
    }
}