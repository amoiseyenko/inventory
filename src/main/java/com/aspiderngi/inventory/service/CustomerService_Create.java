package com.aspiderngi.inventory.service;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.common.service.entity.CustomerConfirmationInfo;
import com.aspiderngi.common.service.entity.CustomerRegistrationInfo;
import com.aspiderngi.common.service.util.SettingsNames;
import com.aspiderngi.inventory.db.dao.CustomerAddressDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerDetailsDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerSettingsDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerStateDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionSimResourceFlatDaoImpl;
import com.aspiderngi.inventory.db.entity.CustomerAddressEntity;
import com.aspiderngi.inventory.db.entity.CustomerDetailsEntity;
import com.aspiderngi.inventory.db.entity.CustomerEntity;
import com.aspiderngi.inventory.db.entity.CustomerSettingsEntity;
import com.aspiderngi.inventory.db.entity.CustomerStateEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionSimResourceFlatEntity;
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
import com.aspiderngi.inventory.service.entity.enums.CustomerState;
import com.aspiderngi.inventory.service.entity.enums.RolesState;
import com.aspiderngi.inventory.service.entity.enums.SubscriptionState;

@Component
public class CustomerService_Create {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService_Create.class);

	@Autowired
	private CustomerDaoImpl customerDao;

	@Autowired
	private SubscriptionDaoImpl subscriptionServiceDao;

	@Autowired
	private CustomerStateDaoImpl customerStateDao;

	@Autowired
	private CustomerSettingsDaoImpl customerSettingDao;

	@Autowired	
	private CustomerDetailsDaoImpl customerDetailsDao;	

	@Autowired
	private CustomerAddressDaoImpl customerAddressDao;
	
	@Autowired 
	private EmailService emailService;
	
	@Autowired
	SubscriptionSimResourceFlatDaoImpl subscriptionSimResourceFlatDao;	
	
	

	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor = { CustomerStateNotFoundException.class,
			SubscriptionNotFoundException.class, 
			EmailAlreadyRegisteredException.class,
			MSISDNAlreadyRegisteredException.class,
			PasswordWeakException.class,
			ServerGenericException.class, 
			SubscriptionInconsistentStateException.class, 
			ResourceConfigNotFoundException.class, 
			SubscriptionResourceNotExistException.class, 
			DatabaseConstraintViolationException.class })
	public CustomerConfirmationInfo createCustomer(CustomerRegistrationInfo customerDetails) throws CustomerStateNotFoundException,
																						SubscriptionNotFoundException, 
																						EmailAlreadyRegisteredException,
																						MSISDNAlreadyRegisteredException,
																						PasswordWeakException,
																						ServerGenericException, 
																						SubscriptionInconsistentStateException, 
																						ResourceConfigNotFoundException, 
																						SubscriptionResourceNotExistException, 
																						DatabaseConstraintViolationException {

		Long customerId = -1L;
		Long customerStateId = new Long(CustomerState.PREUSE.getId());

		String activationTokenGUID = java.util.UUID.randomUUID().toString();

		CustomerEntity customer = new CustomerEntity();

		CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
		CustomerDetailsEntity customerDetailsEntity = new CustomerDetailsEntity();
		CustomerSettingsEntity customerSettingsEntity = new CustomerSettingsEntity();
		
		CustomerStateEntity customerStateEntity = customerStateDao.read(customerStateId);

		if(customerStateEntity == null) {
			logger.warn("Unable to get state by id={} for customer={}", customerStateId, customerDetails);
			throw new CustomerStateNotFoundException(MessageFormat.format("Unable to get state :{0}", customerStateId));
		}

		if(emailService.isInDb(customerDetails.getEmail())) {
			throw new EmailAlreadyRegisteredException("Email already registered");
		}
		if(customerDetails.getPassword().length() < 3) {
			throw new PasswordWeakException("Password is too weak");
		}
		
		customerDetailsEntity.setFirstName(customerDetails.getFirstName());
		customerDetailsEntity.setLastName(customerDetails.getLastName());
		customerDetailsEntity.setPassword(customerDetails.getPassword());
		customerDetailsEntity.setEmail(customerDetails.getEmail().toLowerCase());
		
		customerSettingsEntity.setValue("true");
		customerSettingsEntity.setName(SettingsNames.LOWBALANCENOTIFICATION);
		
		customer.setConfirmationToken(activationTokenGUID);
		customer.setDetails(customerDetailsEntity);
		customer.setAddress(customerAddressEntity);
		customer.setRoleId(RolesState.USER.getId());
		
 
		List<SubscriptionSimResourceFlatEntity> customerResources = subscriptionSimResourceFlatDao.getByMSISDN(customerDetails.getMsisdn());
		SubscriptionSimResourceFlatEntity flatEntity = null;
		if(!customerResources.iterator().hasNext()){
			logger.error("No resources assigned to msisdn {}", customerDetails.getMsisdn());
			throw new ResourceConfigNotFoundException(MessageFormat.format("No resources assigned to msisdn {0}", customerDetails.getMsisdn()));
		}
		flatEntity = customerResources.iterator().next();
		Long subscriptionId = flatEntity.getSubscriptionId(); // customerDetails.getInternalSubscriptionId();
		SubscriptionEntity subscription = subscriptionServiceDao.read(subscriptionId);
		
		if(subscription == null) {
			logger.warn("No subscription is assigned to customer {}", customerDetails);
			throw new SubscriptionResourceNotExistException(MessageFormat.format("No subscription is assigned to customer {0}", customerDetails));
		}

		if(subscription.getSubscriptionState().getID() != SubscriptionState.PREUSE.getId()){
			logger.warn("Subscription is not in PREUSE state");
			throw new SubscriptionInconsistentStateException(MessageFormat.format("Subscription state is {0}, required state is 'PREUSE'", subscription.getSubscriptionState().getID()));
		}

		// TODO: low: add correct check for MSISDNAlreadyRegisteredException. 
		//		if(subscription.getSubscriptionResourceConfiguration().get(0).getMsisdns().size() < 0 ) {
		//			throw new MSISDNAlreadyRegisteredException("MSISDN already registered");
		//		}

		customer.setSubscription(subscription);
		customer.setState(customerStateEntity);

		try {
			customerId = customerDao.create(customer);
			
			customerAddressEntity.setCustomer(customer);
			customerDetailsEntity.setCustomer(customer);
			customerSettingsEntity.setCustomer(customer);
 
			customerAddressDao.create(customerAddressEntity);
			customerDetailsDao.create(customerDetailsEntity);
			customerSettingDao.create(customerSettingsEntity);			
		} catch(ConstraintViolationException exc) {
			logger.error("{}", exc);
			throw new DatabaseConstraintViolationException("Constraint violation: " + exc.getConstraintName().toUpperCase());
		}
		catch(Exception exc){
			throw new ServerGenericException(exc.getMessage());
		}

		return new CustomerConfirmationInfo(customerId, activationTokenGUID);
	}	
}