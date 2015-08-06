package com.aspiderngi.inventory.web.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspiderngi.artifacts.model.CustomerPatchOperation;
import com.aspiderngi.common.service.entity.CustomerConfirmationInfo;
import com.aspiderngi.common.service.entity.CustomerDetails;
import com.aspiderngi.common.service.entity.CustomerFullInfo;
import com.aspiderngi.common.service.entity.CustomerRegistrationInfo;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.db.dao.CustomerDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerDetailsDaoImpl;
import com.aspiderngi.inventory.db.exception.CustomerDetailsNotFoundException;
import com.aspiderngi.inventory.db.exception.CustomerInvalidStateException;
import com.aspiderngi.inventory.db.exception.CustomerInvalidTokenException;
import com.aspiderngi.inventory.db.exception.CustomerNotFoundException;
import com.aspiderngi.inventory.db.exception.CustomerPatchIllegalArguments;
import com.aspiderngi.inventory.db.exception.CustomerStateNotFoundException;
import com.aspiderngi.inventory.db.exception.DatabaseConstraintViolationException;
import com.aspiderngi.inventory.db.exception.EmailAlreadyRegisteredException;
import com.aspiderngi.inventory.db.exception.MSISDNAlreadyRegisteredException;
import com.aspiderngi.inventory.db.exception.PasswordWeakException;
import com.aspiderngi.inventory.db.exception.ResourceConfigNotFoundException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.exception.SubscriptionInconsistentStateException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceNotExistException;
import com.aspiderngi.inventory.service.CustomerPatchService;
import com.aspiderngi.inventory.service.CustomerService_Activate;
import com.aspiderngi.inventory.service.CustomerService_Create;
import com.aspiderngi.inventory.service.CustomerService_Get;

@Component
public class CustomerBean implements CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerBean.class);

	@Autowired
	private CustomerService_Create customerServiceCreate;

	@Autowired
	private CustomerService_Get customerSetviceGet;

	@Autowired
	private CustomerDetailsDaoImpl customerDetailsDao;

	@Autowired
	private CustomerDaoImpl customerDao;
	
	@Autowired
	private CustomerPatchService customerPatchService;

	@Autowired
	private CustomerService_Activate customerServiceActivate;

	@Override
	public OperationResultParam<CustomerConfirmationInfo> createCustomer(@RequestBody CustomerRegistrationInfo  customer) {
		logger.info("Creating new customer: {}", customer);

		CustomerConfirmationInfo customerServiceInfo = new CustomerConfirmationInfo();
		try {
			customerServiceInfo = customerServiceCreate.createCustomer(customer);
		} catch(SubscriptionNotFoundException exc) {
			logger.error("Subscritpion for customer {} not found",customer);
			return new OperationResultParam<CustomerConfirmationInfo>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		} catch(CustomerStateNotFoundException exc) {
			logger.error("Customer state for customer {} not found",customer);
			return new OperationResultParam<CustomerConfirmationInfo>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		} catch(EmailAlreadyRegisteredException exc) {
			logger.error("Email already registered");
			return new OperationResultParam<CustomerConfirmationInfo>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		} catch(MSISDNAlreadyRegisteredException exc) {
			logger.error("MSISDN already registered");
			return new OperationResultParam<CustomerConfirmationInfo>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		}catch(PasswordWeakException exc) {
			logger.error("Password is too weak");
			return new OperationResultParam<CustomerConfirmationInfo>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		}catch (SubscriptionInconsistentStateException exc) {
			logger.error("{}", exc);
			return new OperationResultParam<CustomerConfirmationInfo>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		} catch (DatabaseConstraintViolationException exc) {
			logger.error("{}", exc);
			return new OperationResultParam<CustomerConfirmationInfo>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		} catch(ServerGenericException exc) {
			logger.error(exc.toString());
			return new OperationResultParam<CustomerConfirmationInfo>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		} catch (ResourceConfigNotFoundException exc) {
			logger.error("{}", exc);
			return new OperationResultParam<CustomerConfirmationInfo>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		} catch (SubscriptionResourceNotExistException exc) {
			logger.error("{}", exc);
			return new OperationResultParam<CustomerConfirmationInfo>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		}
		return new OperationResultParam<CustomerConfirmationInfo>("OK", "OK", customerServiceInfo);
	}

	@Override
	public OperationResult activateCustomer(@PathVariable Long customerId, @RequestParam String token) {
		logger.info("Activate Customer: {}, token: {}", customerId, token);
		
		try{
			customerServiceActivate.execute(customerId, token);
		} catch(CustomerNotFoundException exc) {
			logger.error("{}", exc);
			return new OperationResult(exc.EXCEPTION_CODE, exc.getMessage());
		} catch(CustomerInvalidTokenException exc) {
			logger.error("{}", exc);
			return new OperationResult(exc.EXCEPTION_CODE, exc.getMessage());
		} catch(ServerGenericException exc) {
			logger.error("{}", exc);	
			return new OperationResult(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (CustomerInvalidStateException exc) {
			logger.error("{}", exc);
			return new OperationResult(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (CustomerStateNotFoundException exc) {
			logger.error("{}", exc);
			return new OperationResult(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (SubscriptionInconsistentStateException exc) {
			logger.error("{}", exc);
			return new OperationResult(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (SubscriptionNotFoundException exc) {
			logger.error("{}", exc);
			return new OperationResult(exc.EXCEPTION_CODE, exc.getMessage());
		}

		return new OperationResult("OK", "OK");
	}

	@Override
	public OperationResultParam<CustomerDetails> getCustomer(@PathVariable Long customerId) {
		logger.info("Get Customer ( id={} ) Details", customerId);

		CustomerDetails customerDetails = null;
		try{
			customerDetails = customerSetviceGet.getCustomerDetailsByCustomerId(customerId);
		}catch(CustomerNotFoundException exc){
			logger.error("{}", exc);
			return new OperationResultParam<CustomerDetails>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		} catch (CustomerDetailsNotFoundException exc) {
			logger.error("{}", exc);
			return new OperationResultParam<CustomerDetails>(exc.EXCEPTION_CODE, exc.getMessage(), null);
		}

		return new OperationResultParam<CustomerDetails>("OK", "OK", customerDetails);
	}
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public OperationResultParam<CustomerDetails> getByEmail(
			@RequestParam(value = "email", required = true, defaultValue="") String email){
		logger.info("Searching for customer by email={}",email); 

		CustomerDetails customerDetails = null;
		try {
			customerDetails = customerSetviceGet.getCustomerByEmail(email.toLowerCase());
		} catch(ServerGenericException exc){
			logger.error("{}", exc);
			return (OperationResultParam<CustomerDetails>) OperationResultParam.ERROR.setResultCode(exc.EXCEPTION_CODE).setResultMessage(exc.getMessage());
		}
		return new OperationResultParam<CustomerDetails>("OK", "OK", customerDetails);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public OperationResultParam<ArrayList<CustomerFullInfo>> search(
			@RequestParam(value = "firstName", required = false, defaultValue="") String firstName,
			@RequestParam(value = "email", required = false, defaultValue="") String email,
			@RequestParam(value = "msisdn", required = false, defaultValue="") String msisdn) {
		logger.info("Searching for customer by first name={}, email={} or msisdn={}",firstName,email,msisdn);
		ArrayList<CustomerFullInfo> customerInfo = null;
		try{
			customerInfo = customerSetviceGet.search( email,firstName, msisdn);
		}catch(CustomerNotFoundException exc){
			logger.error("{}",exc);
			return new OperationResultParam<ArrayList<CustomerFullInfo>>(exc.EXCEPTION_CODE,"ERROR",null);
		} catch(ServerGenericException exc){
			logger.error("{}",exc);
			return new OperationResultParam<ArrayList<CustomerFullInfo>>(exc.EXCEPTION_CODE,"ERROR",null);
		}
		return new OperationResultParam<ArrayList<CustomerFullInfo>>("OK","OK",customerInfo);
	}

	@Override
	public OperationResultParam<CustomerFullInfo> getDetails(@PathVariable Long customerId) {
		logger.info("Getting full customer info");
		CustomerFullInfo customerInfo = null;
		try{
			CustomerDetails customerDetails = customerSetviceGet.getCustomerDetailsByCustomerId(customerId);
			customerInfo = customerSetviceGet.search(customerDetails.getEmail());
		}catch(CustomerNotFoundException exc){
			logger.error("{}",exc);
			return new OperationResultParam<CustomerFullInfo>(exc.EXCEPTION_CODE,"ERROR",null);
		}catch(ServerGenericException exc){
			logger.error("{}",exc);
			return new OperationResultParam<CustomerFullInfo>(exc.EXCEPTION_CODE,"ERROR",null);
		}catch (CustomerDetailsNotFoundException exc) {
			logger.error("{}",exc);
			return new OperationResultParam<CustomerFullInfo>(exc.EXCEPTION_CODE,"ERROR",null);
		}
		return new OperationResultParam<CustomerFullInfo>("OK","OK",customerInfo);
	}

	@Override
	public OperationResult patchCustomer(@RequestBody CustomerPatchOperation operation) {
		logger.info("Pathing customer with operation: {}",operation);
		OperationResult operationResult = null;
		try{
			operationResult = customerPatchService.execute(operation);
		}catch (CustomerPatchIllegalArguments exc) {
			return new OperationResult().setResultMessage(exc.getMessage()).setResultCode(exc.EXCEPTION_CODE);
		}catch(ServerGenericException exc){
			return new OperationResult().setResultMessage(exc.getMessage()).setResultCode("ERROR");
		}
		return operationResult;
	}
}