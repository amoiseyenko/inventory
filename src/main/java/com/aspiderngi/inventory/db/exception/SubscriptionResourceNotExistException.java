package com.aspiderngi.inventory.db.exception;

public class SubscriptionResourceNotExistException extends Exception {

	private static final long serialVersionUID = 2322964848971524875L;

	public final String EXCEPTION_CODE = "RESOURCE_NOT_EXIST";

	public SubscriptionResourceNotExistException() {}

    public SubscriptionResourceNotExistException(String message)
    {
       super(message);
    }
}