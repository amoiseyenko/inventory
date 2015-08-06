package com.aspiderngi.inventory.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.common.service.entity.Subscription;
import com.aspiderngi.inventory.db.dao.ResourceConfigurationDaoImpl;
import com.aspiderngi.inventory.db.dao.SimResourceStateDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionProviderDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionSimResourceFlatDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionStateDaoImpl;
import com.aspiderngi.inventory.db.entity.ResourceConfigurationEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionProviderEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionSimResourceFlatEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionStateEntity;
import com.aspiderngi.inventory.db.exception.ResourceInconsistentStateException;
import com.aspiderngi.inventory.db.exception.SubscriptionAlreadyExistException;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceExistException;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceFetchException;
import com.aspiderngi.inventory.service.entity.enums.SimResourceState;
import com.aspiderngi.inventory.service.entity.enums.SubscriptionState;

@Component
public class SubscriptionService_Create {

	@Autowired
	SubscriptionProviderDaoImpl subscriptionProviderDao;
	
	@Autowired
	SubscriptionStateDaoImpl subscriptionStateDao;
	
	@Autowired
	SimResourceStateDaoImpl simResourceStateDao;
	
	@Autowired
	SubscriptionDaoImpl subscriptionDao;
	
	@Autowired
	SubscriptionSimResourceFlatDaoImpl subscriptionSimResourceFlatDao;
	
	@Autowired
	ResourceConfigurationDaoImpl resourceConfigurationDao;
	
	private static final Logger logger = LoggerFactory.getLogger(SubscriptionService_Create.class);

	public SubscriptionService_Create() {
		logger.debug("SubscriptionService_Create.ctor");
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Long createSubscription(Subscription model) throws 	SubscriptionAlreadyExistException,
																SubscriptionResourceFetchException,
																SubscriptionResourceExistException,
																ResourceInconsistentStateException {
		logger.trace("SubscriptionService_Create.createSubscription: Subscription = " + model.toString());

		Long subscriptionId = -1L;
		Long start = System.currentTimeMillis();
		
		try {
			SubscriptionProviderEntity spe = this.subscriptionProviderDao.getByName(model.getSubscriptionProviderName());
			SubscriptionStateEntity sse = this.subscriptionStateDao.getByName(SubscriptionState.PREUSE.toString());

			// 1. create subscription
			SubscriptionEntity se = subscriptionDao.getBySubscriptionID(model.getSubscriptionId());
			if(se != null && se.getSubscriptionProvider().getId() == spe.getId())
				throw new SubscriptionAlreadyExistException("Subscription ID=" + model.getSubscriptionId() + " already exist for operator " + spe.getName());
			se = new SubscriptionEntity(model.getSubscriptionId(), spe, sse);
			subscriptionId = subscriptionDao.create(se);

			// 2. assign resource on subscription
			List<SubscriptionSimResourceFlatEntity> simResources = subscriptionSimResourceFlatDao.getByICCID(model.getICCID());
			if(simResources.size() == 0)
				// TODO: SIM_CARD_NOT_FOUND
				throw new SubscriptionResourceFetchException("ICCID Resource doesn't exist. ICCID = " + model.getICCID());
			if(simResources.size() > 1)
				throw new SubscriptionResourceFetchException("ICCID Resource fetching exception. More than one resource exist. ICCID = " + model.getICCID());
			if(simResources.get(0).getIccidState() != SimResourceState.PREACTIVE.getId())
				throw new ResourceInconsistentStateException("Resource ICCID " + model.getICCID() + " is in state " + simResources.get(0).getIccidState());

			// TODO: compare IMSI & MSISDN: error code - INVALID_REQUEST_ARGUMENTS
			// MSISDN Provided (%s) Does not match SIM Cards MSISDN (%s)
			// IMSI Provided (%s) Does not match IMSI Number for SIM Card (%s)
			ResourceConfigurationEntity ssre = resourceConfigurationDao.read(simResources.get(0).getId());
			if(ssre.getSubscription() != null)
				throw new ResourceInconsistentStateException("Resource ID " + ssre.getId() + " already assigned on subscription " + ssre.getSubscription().getId());

			ssre.setSubscription(se);
			
			resourceConfigurationDao.update(ssre);
		} catch (ConstraintViolationException ex) {
			throw new SubscriptionResourceExistException("Subscription or Resource already exists.");
		} catch (Exception ex) {
			throw ex;
		} finally {
			logger.info("Execution time: " + TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - start));
		}

		return subscriptionId;
	}
}