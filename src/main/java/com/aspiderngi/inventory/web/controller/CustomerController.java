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

import com.aspiderngi.artifacts.model.CustomerPatchOperation;
import com.aspiderngi.common.service.entity.CustomerConfirmationInfo;
import com.aspiderngi.common.service.entity.CustomerDetails;
import com.aspiderngi.common.service.entity.CustomerFullInfo;
import com.aspiderngi.common.service.entity.CustomerRegistrationInfo;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;

@RestController
@RequestMapping("/customers")
public interface CustomerController {

	@RequestMapping(value = "",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationResultParam<CustomerConfirmationInfo> createCustomer(@RequestBody CustomerRegistrationInfo customer);
	
	@RequestMapping(value = "",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationResult patchCustomer(@RequestBody CustomerPatchOperation operation);
	
	@RequestMapping(value = "/{customerId}/active",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationResult activateCustomer(@PathVariable Long customerId, @RequestParam(value="token", required=true) String token);

	@RequestMapping(value = "/{customerId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationResultParam<CustomerDetails> getCustomer(@PathVariable Long customerId);
	
	@RequestMapping(value = "/{customerId}/details",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationResultParam<CustomerFullInfo> getDetails(@PathVariable Long customerId);
	
	@RequestMapping(value = "",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			params = {"email"})
	public @ResponseBody OperationResultParam<CustomerDetails> getByEmail(
			@RequestParam(value = "email", required = true, defaultValue="") String email);
	
	@RequestMapping(value = "",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			params = {"firstName","email","msisdn"})
	public @ResponseBody OperationResultParam<ArrayList<CustomerFullInfo>> search(
			@RequestParam(value = "firstName", required = false, defaultValue="") String firstName,
			@RequestParam(value = "email", required = false, defaultValue="") String email,
			@RequestParam(value = "msisdn", required = false, defaultValue="") String msisdn);	
	
	

}