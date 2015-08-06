package com.aspiderngi.inventory.web.controller;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspiderngi.common.service.entity.CustomerNote;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.db.exception.AgentNotFoundException;
import com.aspiderngi.inventory.db.exception.CategoryNotFoundException;
import com.aspiderngi.inventory.db.exception.CustomerNotFoundException;
import com.aspiderngi.inventory.db.exception.CustomerNotesNotFoundException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.service.CustomerNotesService_Get;
import com.aspiderngi.inventory.service.CustomerNotesService_Post;

@Component
public class NotesBean implements NotesController{
	
	
	@Autowired
	private CustomerNotesService_Get customerNotesServiceGet; 
	
	
	@Autowired 
	private CustomerNotesService_Post customerNotesServicePost;
	
	@Override
	public OperationResultParam<List<CustomerNote>> getCustomerNotes(@PathVariable Long customerId,@RequestParam Integer position, 
			@RequestParam Integer itemsCount) {
		List<CustomerNote> notes;
		try{
			notes = customerNotesServiceGet.getNotes(customerId,position,itemsCount);
		}catch(CustomerNotesNotFoundException exc){
			return new OperationResultParam<List<CustomerNote>>("ERROR", "Customer doesn't have notes",null);
		}
		catch( ServerGenericException exc){
			return new OperationResultParam<List<CustomerNote>>("ERROR", "Unable to get customer notes",null);
		}
		return new OperationResultParam<List<CustomerNote>>("OK","OK",notes);
	}


	@Override
	public OperationResult addNote(@RequestBody CustomerNote customerNote) {
		OperationResult operationResult = new OperationResult("OK","OK");
		try{
			Long id = customerNotesServicePost.addNote(customerNote);
		}catch(CustomerNotFoundException exc){
			return new OperationResultParam<List<CustomerNote>>("ERROR", MessageFormat.format("Unable to find customer with id {0}",customerNote.getCustomerId()),null);
		}catch(AgentNotFoundException exc){
			return new OperationResultParam<List<CustomerNote>>("ERROR", MessageFormat.format("Unable to find agent with id {0}",customerNote.getAgentId()),null);
		}catch(CategoryNotFoundException exc){
			return new OperationResultParam<List<CustomerNote>>("ERROR", MessageFormat.format("Unable to find category:{0}",customerNote.getCategory()),null);
		}
		catch( ServerGenericException exc){
			return new OperationResultParam<List<CustomerNote>>("ERROR", "Internal server error",null);
		}
		return operationResult;
	}
 
	
	
}
