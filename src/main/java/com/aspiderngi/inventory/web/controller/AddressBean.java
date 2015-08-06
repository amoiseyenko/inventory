package com.aspiderngi.inventory.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.aspiderngi.common.service.entity.CustomerAddress;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.db.exception.CustomerNotFoundException;
import com.aspiderngi.inventory.service.CustomerAddressService;

@Component
public class AddressBean implements AddressController {

	private static final Logger logger = LoggerFactory.getLogger(AddressBean.class);
		
	@Autowired
	CustomerAddressService customerAddressService;

	@Override
	public OperationResultParam<CustomerAddress> getCustomerAddress(
			@PathVariable Long customerId)  {
		logger.info("Getting address for customer id={}",customerId);
		CustomerAddress customerAddress;
		try {
			customerAddress = customerAddressService.getCustomerAddress(customerId);
		} catch (CustomerNotFoundException exc) {
			logger.warn("{}",exc);
			return new OperationResultParam<CustomerAddress>(exc.EXCEPTION_CODE,exc.getMessage(),null);
		}
		
		return new OperationResultParam<CustomerAddress>("OK","OK",customerAddress);
	}
	
	public OperationResult updateCustomerAddress(@PathVariable Long customerId, @RequestBody CustomerAddress newAddress){
		logger.info("Updating customer address");
		
		OperationResult operationResult = new OperationResult("OK", "OK");
		
		try{
			customerAddressService.updateCustomerAddress(customerId, newAddress);
		}catch(Exception exc){
			logger.warn("{}",exc);
			operationResult.setResultCode("ERROR");
			operationResult.setResultMessage(exc.getMessage());
		}
		return operationResult;
	}
	

}
