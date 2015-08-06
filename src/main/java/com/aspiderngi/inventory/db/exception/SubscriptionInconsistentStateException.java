package com.aspiderngi.inventory.db.exception;

public class SubscriptionInconsistentStateException extends Exception {

	private static final long serialVersionUID = -4647060702744495386L;

	public final String EXCEPTION_CODE = "SUBSCRIPTION_INCONSISTENT";

	public SubscriptionInconsistentStateException() {
	}

	public SubscriptionInconsistentStateException(String message) {
		super(message);
	}
}