package com.aspiderngi.inventory.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aspiderngi.common.service.entity.CustomerAddress;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;

@RestController
@RequestMapping("/addresses")
public interface AddressController {

	@RequestMapping(value = "{customerId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationResultParam<CustomerAddress> getCustomerAddress(@PathVariable Long customerId);
	
	
	@RequestMapping(value = "{customerId}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public OperationResult updateCustomerAddress(@PathVariable Long customerId, @RequestBody CustomerAddress newAddress);
}