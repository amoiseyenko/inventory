package com.aspiderngi.inventory.service;

import java.text.MessageFormat;
import java.util.Date;
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
import com.aspiderngi.inventory.db.entity.ResourceConfigurationEntity;
import com.aspiderngi.inventory.db.entity.SimSwapEntity;
import com.aspiderngi.inventory.db.entity.SimSwapStateEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionSimResourceFlatEntity;
import com.aspiderngi.inventory.db.exception.ResourceConfigNotFoundException;
import com.aspiderngi.inventory.db.exception.ResourceInconsistentStateException;
import com.aspiderngi.inventory.db.exception.SimSwapRequestExistsException;
import com.aspiderngi.inventory.db.exception.SimSwapStatusNotFoundException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;
import com.aspiderngi.inventory.service.entity.enums.SimResourceState;
import com.aspiderngi.inventory.service.entity.enums.SimSwapState;

@Component
public class SimSwapService_Create {
	
	private static final Logger logger = LoggerFactory.getLogger(SimSwapService_Create.class);
	
	@Autowired
	SubscriptionDaoImpl subscriptionDao;

	@Autowired
	SubscriptionSimResourceFlatDaoImpl resourceConfigFlatDao;
	
	@Autowired
	ResourceConfigurationDaoImpl rcDao;
 
	@Autowired
	SimSwapStateDaoImpl simSwapStatusDao;
	
	@Autowired
	SimSwapDaoImpl simswapRequestDao;

	public SimSwapService_Create() {
		logger.debug("SimSwapService_Create.ctor");
	}
	
	@Transactional
	public Long createRequest(Long subscriptionId, String iccId) throws SubscriptionNotFoundException, ResourceConfigNotFoundException, SimSwapStatusNotFoundException, ResourceInconsistentStateException,SimSwapRequestExistsException {
		logger.info("Creating simswap request for subscriptionId={}, iccid={}", subscriptionId, iccId);
		
		Long start = System.currentTimeMillis();

		try {
			SubscriptionEntity subscription = subscriptionDao.getByInnerSubscriptionID(subscriptionId);

			if (subscription == null){
				logger.warn("Unable to find subscription with id: " + subscriptionId);
				throw new SubscriptionNotFoundException(MessageFormat.format("Subscription with id {0} not found", subscriptionId));
			}

			// check if request for this iccid and subscription already in DB. skip duplicates.
			SimSwapEntity tempSimSwapEntity = simswapRequestDao.getBySubscriptionIdAndIccId(subscription, iccId);
			if(tempSimSwapEntity != null ){
				logger.warn("SimSwap request for subscription={} and iccid={} already exists", subscription.toString(), iccId);
				throw new SimSwapRequestExistsException();
			}
			
			// get resource from flat and check state must be pre-active
			List<SubscriptionSimResourceFlatEntity> resourceConfigurations = resourceConfigFlatDao.getByICCID(iccId);
			
			if (resourceConfigurations.isEmpty()){
				logger.warn("Unable to find resouce configuration for iccid {}", subscriptionId, iccId);
				throw new ResourceConfigNotFoundException(MessageFormat.format("Resource with iccId {0} not found", iccId));
			}
	
			SubscriptionSimResourceFlatEntity iccid = resourceConfigurations.iterator().next();
			
			if(iccid.getIccidState() != SimResourceState.PREACTIVE.getId()){
				logger.warn("ICCID has invalid state : {}", iccid.getIccidState());
				throw new ResourceInconsistentStateException(MessageFormat.format("Resource {0} is in invalid state - {1}",iccid.toString(),iccid.getIccidState()));
			} 
	
			SimSwapStateEntity simswapStatusInit = simSwapStatusDao.getStateByName(SimSwapState.INIT.name());
			if(simswapStatusInit == null)
				throw new SimSwapStatusNotFoundException("Cannot find status for new simswap request");

			ResourceConfigurationEntity rc = rcDao.read(iccid.getId());

			SimSwapEntity simswapRequest = new SimSwapEntity(new Date(), iccId, subscription, rc, simswapStatusInit);
			Long simswapRequestId = simswapRequestDao.create(simswapRequest);

			logger.info("Storing simswap request {} with id {} ", simswapRequest, simswapRequestId);

			return simswapRequestId;
		} catch (Exception ex) {
			throw ex;
		} finally {
			logger.info("Execution time: " + TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - start));
		}
	}

}