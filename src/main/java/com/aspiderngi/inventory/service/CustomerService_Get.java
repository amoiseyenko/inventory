package com.aspiderngi.inventory.service;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.common.service.entity.CustomerDetails;
import com.aspiderngi.common.service.entity.CustomerFullInfo;
import com.aspiderngi.inventory.db.dao.CustomerDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerDetailsFlatDaoImpl;
import com.aspiderngi.inventory.db.entity.CustomerDetailsFlatEntity;
import com.aspiderngi.inventory.db.entity.CustomerEntity;
import com.aspiderngi.inventory.db.exception.CustomerDetailsNotFoundException;
import com.aspiderngi.inventory.db.exception.CustomerNotFoundException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.utils.PopulationUtils;

@Component
public class CustomerService_Get {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService_Get.class);

	@Autowired
	CustomerDaoImpl customerDao;

	@Autowired
	CustomerDetailsFlatDaoImpl customerDetailsFlatDao;

	@Transactional
	public CustomerDetails getCustomerByEmail(String email) throws ServerGenericException {
		CustomerEntity customerEntity = null;
		CustomerDetails customerDetails = null;
		try{
			customerEntity = customerDao.getByEmail(email);
			if(customerEntity == null) {
				throw new Exception(MessageFormat.format("There is no user customer entity with email email {} in DB", email));
			}
			customerDetails = PopulationUtils.populateCustomerDetails(customerEntity);
		}catch(Exception exc){
			logger.error("Unable to find customer by email = {}", email);
			throw new ServerGenericException(MessageFormat.format("Cannot find customer with email = {0}", email));
		}

		return customerDetails;
	}


	@Transactional
	public CustomerDetails getCustomerDetailsByCustomerId(Long customerId) throws CustomerNotFoundException, CustomerDetailsNotFoundException {
		CustomerEntity customer = customerDao.read(customerId);
		if(customer == null){
			logger.error("Unable to find customer with id={}", customerId);
			throw new CustomerNotFoundException(MessageFormat.format("Cannot find customer with id={0}", customerId));
		}
		CustomerDetails customerDetails = PopulationUtils.populateCustomerDetails(customer);

		return customerDetails;
	}
	
	@Transactional
	public CustomerFullInfo search(String email) throws ServerGenericException,CustomerNotFoundException{
		return this.search(email,"","").get(0);
	}
	

	@Transactional
	public ArrayList<CustomerFullInfo> search(String email,String firstName,String msisdn) throws ServerGenericException,CustomerNotFoundException{
			ArrayList<CustomerDetailsFlatEntity> entity = new ArrayList<CustomerDetailsFlatEntity>();
			entity = customerDetailsFlatDao.getCustomerArrayByStrictParameters(email,firstName,msisdn);
//			if(!email.isEmpty()){
//				entity = customerDetailsFlatDao.getCustomerArrayByEmail(email);
//			}
//			if(!msisdn.isEmpty()){
//				entity.addAll(customerDetailsFlatDao.getCustomerArrayByMsisdn(msisdn)); 
//			}
//			else if(!firstName.isEmpty()){
//				entity.addAll(customerDetailsFlatDao.getCustomerArrayByFirstName(firstName)); 
//			}
			if(entity == null || entity.size() == 0)
				throw new CustomerNotFoundException(MessageFormat.format("Unable to find customer info by parameters: [ email: {0}, firstName: {1}, msisdn: {2} ]", email,firstName,msisdn));
			return  PopulationUtils.populateCustomerInfoArray(entity);

	}

	
}