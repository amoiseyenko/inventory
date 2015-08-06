package com.aspiderngi.inventory.db.exception;

public class AgentNotFoundException extends Exception {

	 
	private static final long serialVersionUID = -7860182927877419770L;
	
	
	public final String EXCEPTION_CODE = "AGENT_NOT_FOUND";
	
	public AgentNotFoundException() {
	}
	
	public AgentNotFoundException(String message){
		super(message);
	}
	
}
