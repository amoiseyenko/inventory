package com.aspiderngi.inventory.db.exception;

public class SubscriptionResourceFetchException extends Exception {

	private static final long serialVersionUID = 7734934684560699008L;

	public final String EXCEPTION_CODE = "RESOURCE_CORRUPTED";

	public SubscriptionResourceFetchException() {}

    public SubscriptionResourceFetchException(String message)
    {
       super(message);
    }
}