package com.aspiderngi.inventory.web.controller;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aspiderngi.common.service.entity.Topup;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;

@RestController
@RequestMapping("/topup")
public interface TopupController {

	@RequestMapping(value = "",
				method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<Long> startTopup(@RequestBody Topup topup);

	@RequestMapping(value = "/state/{stateId}/{transactionId}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResult finishTopup(@PathVariable Integer stateId, @PathVariable String transactionId);

	@RequestMapping(value = "/{customerId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<ArrayList<Topup>> getHistory(@PathVariable Long customerId, 
			@RequestParam(value = "count", required = false, defaultValue="5") Integer count);

	
	@RequestMapping(value = "/{customerId}/count/{limit}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<Integer> getSuccessfulCount(@PathVariable Long customerId, @PathVariable int limit);

}