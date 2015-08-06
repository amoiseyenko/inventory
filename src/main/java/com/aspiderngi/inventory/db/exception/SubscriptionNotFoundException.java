package com.aspiderngi.inventory.db.exception;

public class SubscriptionNotFoundException extends Exception {

	private static final long serialVersionUID = 1400294347823467552L;
	public static final String EXCEPTION_CODE = "SUBSCRIPTION_NOT_FOUND";

	public SubscriptionNotFoundException() {}

    public SubscriptionNotFoundException(String message) {
       super(message);
    }
}