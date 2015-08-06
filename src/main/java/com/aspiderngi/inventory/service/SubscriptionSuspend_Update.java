package com.aspiderngi.inventory.service;

import java.text.MessageFormat;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aspiderngi.artifacts.model.SuspendRequestModel;
import com.aspiderngi.common.service.entity.enums.SubscriptionSuspendType;
import com.aspiderngi.inventory.db.dao.SubscriptionDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionProviderDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionStateDaoImpl;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionProviderEntity;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;
import com.aspiderngi.inventory.db.exception.SubscriptionStateNotFoundException;

@Component
public class SubscriptionSuspend_Update {

	private static final Logger logger = LoggerFactory.getLogger(SubscriptionSuspend_Update.class);

	@Autowired
	private SubscriptionDaoImpl subscriptionDaoImpl;

	@Autowired
	private SubscriptionStateDaoImpl subsctiptionStateDaoImpl;

 	@Autowired
	private SubscriptionProviderDaoImpl subscriptionProviderDao;

	@SuppressWarnings("unchecked")
	@Transactional
	public void updateSuspendState(String subscriptionId, String spName, SuspendRequestModel suspendRequest) 
																		throws 	SubscriptionNotFoundException, 
																				SubscriptionStateNotFoundException, 
																				ServerGenericException  {

		try {
			SubscriptionEntity se = subscriptionDaoImpl.getBySubscriptionID(subscriptionId);
			SubscriptionProviderEntity spe = subscriptionProviderDao.getByName(spName);
			
			if(se == null)
				throw new SubscriptionNotFoundException(MessageFormat.format("Unable to find subscription id:{0}", subscriptionId));
			if(se != null && se.getSubscriptionProvider().getId() != spe.getId())
				throw new SubscriptionNotFoundException("Subscription ID=" + subscriptionId + " not found for provider '" + spName + "'");
			
			if(null != suspendRequest.getSetSuspends()) {
				for(Integer state : suspendRequest.getSetSuspends()){
					se.getSubscriptionSuspendState().add(SubscriptionSuspendType.GetValue(state));
				}
			}
			
			if(null != suspendRequest.getUnsetSuspends()) {
				for(Integer state : suspendRequest.getUnsetSuspends()) {
					se.getSubscriptionSuspendState().remove(SubscriptionSuspendType.GetValue(state));
				}
			}

			subscriptionDaoImpl.update(se);
		} catch(Exception exc) {
			logger.error("{}",exc);
			throw new ServerGenericException(exc.getMessage());
		}
	}
}