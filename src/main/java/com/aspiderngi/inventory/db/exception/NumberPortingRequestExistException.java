package com.aspiderngi.inventory.db.exception;

public class NumberPortingRequestExistException extends Exception {

	private static final long serialVersionUID = 2615441728779106447L;

	public final String EXCEPTION_CODE = "NP_REQUEST_ALREADY_PENDING";

	public NumberPortingRequestExistException() {}

    public NumberPortingRequestExistException(String message)
    {
       super(message);
    }
}