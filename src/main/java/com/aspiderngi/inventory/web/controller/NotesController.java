package com.aspiderngi.inventory.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspiderngi.common.service.entity.CustomerNote;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;

@RestController
@RequestMapping("/notes")
public interface NotesController {
	
	@RequestMapping(value = "{customerId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
					params={"position","itemsCount"})
	public OperationResultParam<List<CustomerNote>> getCustomerNotes(@PathVariable Long customerId,@RequestParam Integer position, 
			@RequestParam Integer itemsCount);
	
	@RequestMapping(value = "",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationResult addNote(@RequestBody CustomerNote customerNote);
	
	
}
