package com.aspiderngi.inventory.service;

import java.text.MessageFormat;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aspiderngi.inventory.db.dao.SubscriptionDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionProviderDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionStateDaoImpl;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionProviderEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionStateEntity;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;
import com.aspiderngi.inventory.db.exception.SubscriptionStateNotFoundException;
import com.aspiderngi.inventory.service.entity.enums.SubscriptionState;

@Component
public class SubscriptionService_Terminate {

	private static final Logger logger = LoggerFactory.getLogger(SubscriptionService_Terminate.class);

	@Autowired
	private SubscriptionDaoImpl subscriptionDaoImpl;

	@Autowired
	private SubscriptionStateDaoImpl subsctiptionStateDaoImpl;
	
 	@Autowired
	private SubscriptionProviderDaoImpl subscriptionProviderDao;

	@Transactional
	public void terminateSubscription(String subscriptionId, String spName) throws SubscriptionNotFoundException,SubscriptionStateNotFoundException, ServerGenericException  {

		try {
			SubscriptionEntity se = subscriptionDaoImpl.getBySubscriptionID(subscriptionId);
			SubscriptionProviderEntity spe = subscriptionProviderDao.getByName(spName);
			
			if(se == null)
				throw new SubscriptionNotFoundException(MessageFormat.format("Unable to find subscription id:{0}", subscriptionId));
			if(se != null && se.getSubscriptionProvider().getId() != spe.getId())
				throw new SubscriptionNotFoundException("Subscription ID=" + subscriptionId + " not found for provider '" + spName + "'");
			
			SubscriptionStateEntity subscriptionStateTerminated = subsctiptionStateDaoImpl.read(new Long(SubscriptionState.TERMINATED.getId()));
			if(subscriptionStateTerminated == null)
				throw new SubscriptionStateNotFoundException(MessageFormat.format("Unable to find subsctiption state {0}",SubscriptionState.getStringValue(SubscriptionState.TERMINATED.getId())));

			se.setSubscriptionState(subscriptionStateTerminated);
			
			subscriptionDaoImpl.update(se);
		} catch(Exception exc) {
			logger.error("{}",exc);
			throw new ServerGenericException(exc.getMessage());
		}
	}
}