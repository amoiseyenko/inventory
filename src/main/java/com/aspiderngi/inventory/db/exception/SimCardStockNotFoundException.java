package com.aspiderngi.inventory.db.exception;

public class SimCardStockNotFoundException extends Exception {

	private static final long serialVersionUID = -5648606980233973619L;

	public final String EXCEPTION_CODE = "SIM_CARD_NOT_FOUND";

	public SimCardStockNotFoundException() {}

    public SimCardStockNotFoundException(String message)
    {
       super(message);
    }
}