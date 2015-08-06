package com.aspiderngi.inventory.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.inventory.db.dao.ResourceConfigurationDaoImpl;
import com.aspiderngi.inventory.db.dao.SimSwapDaoImpl;
import com.aspiderngi.inventory.db.dao.SimSwapStateDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionDaoImpl;
import com.aspiderngi.inventory.db.dao.SubscriptionSimResourceFlatDaoImpl;
import com.aspiderngi.inventory.db.entity.SimSwapEntity;
import com.aspiderngi.inventory.db.entity.SimSwapStateEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionSimResourceFlatEntity;
import com.aspiderngi.inventory.db.exception.ResourceConfigNotFoundException;
import com.aspiderngi.inventory.db.exception.ResourceInconsistentStateException;
import com.aspiderngi.inventory.db.exception.SimSwapRequestNotFoundException;
import com.aspiderngi.inventory.db.exception.SimSwapStatusNotFoundException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;
import com.aspiderngi.inventory.service.entity.enums.SimResourceState;
import com.aspiderngi.inventory.service.entity.enums.SimSwapState;

@Component
public class SimSwapService_Confirm {
	
	private static final Logger logger = LoggerFactory.getLogger(SimSwapService_Confirm.class);
	
	@Autowired
	SubscriptionDaoImpl subscriptionDao;

	@Autowired
	ResourceConfigurationDaoImpl resourceConfigDao;
	
	@Autowired
	SimSwapStateDaoImpl simSwapStatusDao;
	
	@Autowired
	SimSwapDaoImpl simswapRequestDao;

	@Autowired
	SubscriptionSimResourceFlatDaoImpl resourceConfigFlatDao;
	 
	public SimSwapService_Confirm() {
		logger.debug("SimSwapService_Confirm.ctor");
	}
	
	@Transactional
	public Long confirmRequest(Long subscriptionId, String iccId) throws SubscriptionNotFoundException, ResourceConfigNotFoundException, ResourceInconsistentStateException, SimSwapRequestNotFoundException, SimSwapStatusNotFoundException {
		
		logger.info(MessageFormat.format("Confirming request for iccid={0}, subscriptionId={1}", iccId, subscriptionId));
		
		Long simswapRequestId = 0L;
		int amountDiscarded = 0;
		Long start = System.currentTimeMillis();		

		try {
			if(subscriptionId == -1L || iccId == null || iccId.isEmpty()) {
				throw new SubscriptionNotFoundException("Mailformed request");
			}
			
			SubscriptionEntity subscription = subscriptionDao.getByInnerSubscriptionID(subscriptionId);
	
			if (subscription == null){
				logger.warn("Unable to find subscription with id: " + subscriptionId);
				throw new SubscriptionNotFoundException(MessageFormat.format("Subscription with id {0} not found", subscriptionId));
			}
			
			List<SubscriptionSimResourceFlatEntity> resourceConfigurations = resourceConfigFlatDao.getByICCID(iccId);
				
			if (resourceConfigurations.isEmpty()){
				logger.warn("Unable to find resouce configuration for iccid {}", subscriptionId, iccId);
				throw new ResourceConfigNotFoundException(MessageFormat.format("Resource with iccId {0} not found", iccId));
			}
			
			SubscriptionSimResourceFlatEntity iccid = resourceConfigurations.iterator().next();
			
			if(iccid.getIccidState() != SimResourceState.PREACTIVE.getId()) {
				logger.warn("ICCID has invalid state : {}", iccid.getIccidState());
				throw new ResourceInconsistentStateException(MessageFormat.format("Resource {0} is in invalid state - {1}", iccid.toString(), iccid.getIccidState()));
			}
	
			List<SimSwapEntity> simSwapRequests = simswapRequestDao.getListForSubscriptionId(subscriptionId);
	
			logger.info("{} swap requests for subscription {}", simSwapRequests.size(), subscriptionId);
	
			SimSwapStateEntity simswapStatusInit 	  = simSwapStatusDao.getStateByName(SimSwapState.INIT.name());
			SimSwapStateEntity simswapStatusCancelled = simSwapStatusDao.getStateByName(SimSwapState.CANCELED.name());
			SimSwapStateEntity simswapStatusCompleted = simSwapStatusDao.getStateByName(SimSwapState.COMPLETED.name());
			if(simswapStatusInit == null || simswapStatusCancelled == null || simswapStatusCompleted == null)
				throw new SimSwapStatusNotFoundException("Cannot find status for new simswap request");
			
			for(SimSwapEntity swapEntity : simSwapRequests) {
				if(swapEntity.getSimSwapState().getID() == simswapStatusInit.getID()) {
					if(swapEntity.getIccid().equals(iccId)) {
						simswapRequestId = swapEntity.getId();
						break;
					}
				}
			}
	
			if (simswapRequestId == 0) {
				logger.warn("Subscription {} has no SimSwap (status INIT) request with ICCID : {}", subscriptionId, iccid.getIccidState());
				throw new SimSwapRequestNotFoundException(MessageFormat.format("Subscription {0} has no SimSwap (status INIT) request with ICCID : {1}", subscriptionId, iccid.getIccidState()));
			}
			
			for(SimSwapEntity swapEntity : simSwapRequests) {
				if(swapEntity.getSimSwapState().getID() == simswapStatusInit.getID()) {
					if(swapEntity.getIccid().equals(iccId)) {
						swapEntity.setSimSwapState(simswapStatusCompleted);
					} else {
						amountDiscarded++;
						swapEntity.setSimSwapState(simswapStatusCancelled);
					}
	
					simswapRequestDao.create(swapEntity);
				}
			}
			
			return simswapRequestId;
		} catch (Exception ex) {
			throw ex;
		} finally {
			logger.info("Execution time: " + TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - start));
		}
	}
}