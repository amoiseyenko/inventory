package com.aspiderngi.inventory.db.exception;

public class CategoryNotFoundException extends Exception {
 
	private static final long serialVersionUID = 2534573418924042381L;
	public final String EXCEPTION_CODE = "CATEGORY_NOT_FOUND";
	
	public CategoryNotFoundException() {
	}
	
	public CategoryNotFoundException(String message){
		super(message);
	}
	
}
