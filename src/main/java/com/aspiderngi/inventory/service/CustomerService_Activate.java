package com.aspiderngi.inventory.service;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.inventory.db.dao.CustomerDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerSettingsDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerStateDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionDaoImpl;
import com.aspiderngi.inventory.db.entity.CustomerEntity;
import com.aspiderngi.inventory.db.entity.CustomerStateEntity;
import com.aspiderngi.inventory.db.exception.CustomerInvalidStateException;
import com.aspiderngi.inventory.db.exception.CustomerInvalidTokenException;
import com.aspiderngi.inventory.db.exception.CustomerNotFoundException;
import com.aspiderngi.inventory.db.exception.CustomerStateNotFoundException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.exception.SubscriptionInconsistentStateException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;
import com.aspiderngi.inventory.service.entity.enums.CustomerState;

@Component
public class CustomerService_Activate   {

	private static final Logger log = LoggerFactory.getLogger(SubscriptionService_Activate.class);

	@Autowired
	private CustomerDaoImpl customerDao;

	@Autowired
	private SubscriptionDaoImpl subscriptionServiceDao;

	@Autowired
	private CustomerStateDaoImpl customerStateDao;

	@Autowired
	private CustomerSettingsDaoImpl customerSettingDao;

	@Autowired
	private SubscriptionService_Activate subscriptionServiceActivate;
	
	CustomerService_Activate(){
		log.debug("CustomerService_Activate.ctor");
	}

	@Transactional
	public void execute(Long customerId, String token) throws CustomerInvalidTokenException, 
																CustomerInvalidStateException,
																CustomerNotFoundException, 
																CustomerStateNotFoundException, 
																ServerGenericException, 
																SubscriptionInconsistentStateException, 
																SubscriptionNotFoundException {
		log.trace("CustomerService_Activate:activateCustomer, customerId = " + customerId + " and token = " + token);
		// base.execute () base is SubscriptionService_Activate
		
		CustomerEntity customer = customerDao.read(customerId);
		if (customer == null) {
			log.warn("Customer with id = {} not found", customerId);
			throw new CustomerNotFoundException(MessageFormat.format("Unable to find customer for id = {0}", customerId));
		}
		
		CustomerStateEntity customerStatePreUse = customerStateDao.read(Long.valueOf(CustomerState.PREUSE.getId()));
		CustomerStateEntity customerStateActive = customerStateDao.read(Long.valueOf(CustomerState.ACTIVE.getId()));
		
		if(customerStatePreUse == null) {
			log.warn("Customer state for customerId = {} not found", customerId);
			throw new CustomerStateNotFoundException(MessageFormat.format("Unable to find customer state for customerId={1}", customerId));
		}

		if(!customer.getConfirmationToken().equals(token)) {
			throw new CustomerInvalidTokenException();
		}

		if(customer.getState() != customerStatePreUse) {
			throw new CustomerInvalidStateException();
		}		

		// delegate activation of subscription to parent class. 
		// if any exceptions will occurs we handle them one level up.
		subscriptionServiceActivate.activateSubscription(customer.getSubscription().getSubscriptionID(), "simpel-pre");
		customer.setState(customerStateActive);
		
		try{
			customerDao.update(customer);
		}catch(Exception exc){
			// wrap exception message and re-throw
			log.error("{}", exc);
			throw new ServerGenericException(exc.getMessage());
		}
	}
}