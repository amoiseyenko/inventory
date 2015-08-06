package com.aspiderngi.inventory.service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.inventory.db.dao.SimResourceStateDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionProviderDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionSimResourceFlatDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionStateDaoImpl;
import com.aspiderngi.inventory.db.entity.ResourceConfigurationEntity;
import com.aspiderngi.inventory.db.entity.ResourceConfigurationStateEntity;
import com.aspiderngi.inventory.db.entity.ResourceICCIDEntity;
import com.aspiderngi.inventory.db.entity.ResourceIMSIEntity;
import com.aspiderngi.inventory.db.entity.ResourceMSISDNEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionProviderEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionStateEntity;
import com.aspiderngi.inventory.db.exception.SubscriptionInconsistentStateException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;
import com.aspiderngi.inventory.service.entity.enums.SimResourceState;
import com.aspiderngi.inventory.service.entity.enums.SubscriptionState;

@Component
public class SubscriptionService_Activate {

	private static final Logger log = LoggerFactory.getLogger(SubscriptionService_Activate.class);
	
 	@Autowired
	private SubscriptionProviderDaoImpl subscriptionProviderDao;
	
	@Autowired
	private SubscriptionDaoImpl subscriptionDao;
	
	@Autowired
	private SubscriptionStateDaoImpl subscriptionStateDao;
	
	@Autowired
	private SubscriptionSimResourceFlatDaoImpl subscriptionSimResourceFlatDao;
	
	@Autowired
	private SimResourceStateDaoImpl resourceStateDao;

	public SubscriptionService_Activate() {
		log.debug("SubscriptionService_Activate.ctor");
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void activateSubscription(String subcriptionId, String subcriptionProviderName) throws 	SubscriptionInconsistentStateException,
																									SubscriptionNotFoundException {
		log.trace("SubscriptionService_Activate.activateSubscription: SubscriptionID = " + subcriptionId + " for provider '" + subcriptionProviderName + "'");
		
		Long start = System.currentTimeMillis();
		
		try {

			SubscriptionProviderEntity spe = subscriptionProviderDao.getByName(subcriptionProviderName);
			
			SubscriptionEntity se = subscriptionDao.getBySubscriptionID(subcriptionId);
			
			if(se == null)
				throw new SubscriptionNotFoundException("Subscription with ID = " + subcriptionId + " not found");
			if(se != null && se.getSubscriptionProvider().getId() != spe.getId())
				throw new SubscriptionNotFoundException("Subscription ID=" + subcriptionId + " not found for provider '" + subcriptionProviderName + "'");
			if(se != null && se.getSubscriptionState().getID() == SubscriptionState.ACTIVE.getId())
				throw new SubscriptionInconsistentStateException("Subscription ID=" + subcriptionId + " is already ACTIVE");
			if(se != null && se.getSubscriptionState().getID() != SubscriptionState.PREUSE.getId())
				throw new SubscriptionInconsistentStateException("Subscription ID=" + subcriptionId + " is in [" + se.getSubscriptionState().getID() + "] state instead of 'PREUSE'");
			
			List<ResourceConfigurationEntity> resourceConfigList = se.getSubscriptionResourceConfiguration();
			
			SubscriptionStateEntity activeSubscriptionState = subscriptionStateDao.getByName(SubscriptionState.ACTIVE.toString());
			ResourceConfigurationStateEntity activeRCState = resourceStateDao.getByName(SimResourceState.ACTIVE.toString());

			Iterator<ResourceICCIDEntity> iter = resourceConfigList.get(0).getIccids().iterator();
			while(iter.hasNext()){
				ResourceICCIDEntity iccidEntity = iter.next();
				iccidEntity.setResourceState(activeRCState);
			}
			Iterator<ResourceIMSIEntity> subscriptionIMSISIterator = resourceConfigList.get(0).getImsis().iterator();
			while(subscriptionIMSISIterator.hasNext()){
				ResourceIMSIEntity imsiEntity = subscriptionIMSISIterator.next();
				imsiEntity.setResourceState(activeRCState);
			}
			Iterator<ResourceMSISDNEntity> subscriptionMSISDNIterator = resourceConfigList.get(0).getMsisdns().iterator();
			while(subscriptionMSISDNIterator.hasNext()){
				ResourceMSISDNEntity msisdnEntity = subscriptionMSISDNIterator.next();
				msisdnEntity.setResourceState(activeRCState);
			}			

			se.setSubscriptionState(activeSubscriptionState);
			
			subscriptionDao.update(se);
		} catch (Exception ex) {
			log.error("{}",ex);
			throw ex;
		} finally {
			log.info("Execution time: " + TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - start));
		}
	}
}