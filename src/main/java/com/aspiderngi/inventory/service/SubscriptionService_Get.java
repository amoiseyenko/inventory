package com.aspiderngi.inventory.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.common.service.entity.Subscription;
import com.aspiderngi.inventory.db.dao.ResourceConfigurationDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionProviderDaoImpl;
import com.aspiderngi.inventory.db.entity.ResourceConfigurationEntity;
import com.aspiderngi.inventory.db.entity.ResourceICCIDEntity;
import com.aspiderngi.inventory.db.entity.ResourceIMSIEntity;
import com.aspiderngi.inventory.db.entity.ResourceMSISDNEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionProviderEntity;
import com.aspiderngi.inventory.db.exception.SubscriptionInconsistentStateException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;

@Component
public class SubscriptionService_Get {

	@Autowired
	SubscriptionProviderDaoImpl subscriptionProviderDao;
	
	@Autowired
	SubscriptionDaoImpl subscriptionDao;
	
	@Autowired
	ResourceConfigurationDaoImpl resourceConfigurationDao;
	
	private static final Logger logger = LoggerFactory.getLogger(SubscriptionService_Get.class);

	public SubscriptionService_Get() {
		logger.debug("SubscriptionService_Get.ctor");
	}

	@Transactional
	public Subscription getSubscription(String subscriptionId, String subcriptionProviderName) throws 	SubscriptionInconsistentStateException,
																										SubscriptionNotFoundException {
		logger.trace("SubscriptionService_Get.getSubscription: SubscriptionID = " + subscriptionId + " for provider '" + subcriptionProviderName + "'");
		
		Long start = System.currentTimeMillis();
		
		try {			
			SubscriptionProviderEntity suscriptionProvider = subscriptionProviderDao.getByName(subcriptionProviderName);
			SubscriptionEntity subscription = subscriptionDao.getBySubscriptionID(subscriptionId);

			if(subscription == null)
				throw new SubscriptionNotFoundException("Subscription ID=" + subscriptionId + " not found");

			if(subscription != null && subscription.getSubscriptionProvider().getId() != suscriptionProvider.getId())
				throw new SubscriptionNotFoundException("Subscription ID=" + subscriptionId + " not found for provider '" + subcriptionProviderName + "'");

			ResourceConfigurationEntity resourceConfiguration = resourceConfigurationDao.getBySubscriptionId(subscription.getId());

			// we assume that Subscription can have only one RC with 1 MSISDN, IMSI, ICCID 
			assert (resourceConfiguration.getIccids().size() == 1);
			assert (resourceConfiguration.getMsisdns().size() == 1);
			assert (resourceConfiguration.getImsis().size() == 1);

			ResourceICCIDEntity iccid = resourceConfiguration.getIccids().iterator().next();
			ResourceMSISDNEntity msisdn = resourceConfiguration.getMsisdns().iterator().next();
			ResourceIMSIEntity imsi = resourceConfiguration.getImsis().iterator().next();
			
			return new Subscription(subscription.getId(), msisdn.getMSISDN().toString(), iccid.getICCID(), imsi.getIMSI(), subscription.getSubscriptionID(), subscription.getSubscriptionProvider().getName());
		} catch (Exception ex) {
			throw ex;
		} finally {
			logger.info("Execution time: " + TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - start));
		}		
	}
}