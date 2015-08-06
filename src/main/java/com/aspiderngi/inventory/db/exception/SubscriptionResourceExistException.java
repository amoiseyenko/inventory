package com.aspiderngi.inventory.db.exception;

public class SubscriptionResourceExistException extends Exception {

	private static final long serialVersionUID = 2322964848971524874L;

	public final String EXCEPTION_CODE = "RESOURCE_EXIST";

	public SubscriptionResourceExistException() {}

    public SubscriptionResourceExistException(String message)
    {
       super(message);
    }
}