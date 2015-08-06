package com.aspiderngi.inventory.db.exception;

public class SimCardStockPersistException extends Exception {

	private static final long serialVersionUID = 6873653552963650865L;

	public final String EXCEPTION_CODE = "SIM_CARD_STOCK_PERSIST_EXCEPTION";

	String log;
	
	public SimCardStockPersistException() {
	}

	public SimCardStockPersistException(String message, String log) {
		super(message);
		
		this.log = log;
	}
}