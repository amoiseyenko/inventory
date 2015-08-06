package com.aspiderngi.inventory.db.exception;

public class ResourceInconsistentStateException extends Exception {

	private static final long serialVersionUID = 8158571594238183987L;

	public final String EXCEPTION_CODE = "RESOURCE_INCONSISTENT";

	public ResourceInconsistentStateException() {
	}

	public ResourceInconsistentStateException(String message) {
		super(message);
	}
}