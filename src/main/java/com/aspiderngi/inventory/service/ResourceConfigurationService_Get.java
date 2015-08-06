package com.aspiderngi.inventory.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.common.service.entity.SimCardResourceFlat;
import com.aspiderngi.inventory.db.dao.SubscriptionSimResourceFlatDaoImpl;
import com.aspiderngi.inventory.db.entity.SubscriptionSimResourceFlatEntity;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceFetchException;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceNotExistException;
import com.aspiderngi.inventory.service.entity.enums.SimResourceState;

@Component
public class ResourceConfigurationService_Get {

	@Autowired
	private SubscriptionSimResourceFlatDaoImpl subscriptionSimResourceFlatDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceConfigurationService_Get.class);

	public ResourceConfigurationService_Get() {
		logger.debug("ResourceConfigurationService_Get.ctor");
	}

	@Transactional
	public SimCardResourceFlat getByICCID(String iccid) throws SubscriptionResourceFetchException, SubscriptionResourceNotExistException {
		logger.trace("ResourceConfigurationService_Get.getByICCID: iccid = " + iccid);

		SimCardResourceFlat result = null;
		Long start = System.currentTimeMillis();

		try {
			
			List<SubscriptionSimResourceFlatEntity> simResources = subscriptionSimResourceFlatDao.getByICCID(iccid);
			
			if(simResources.size() == 0)
				throw new SubscriptionResourceNotExistException("ICCID Resource lookup exception: doesn't exist");
			if(simResources.size() != 1)
				throw new SubscriptionResourceFetchException("ICCID Resource lookup exception: there are more than 1 resource");

			result = new SimCardResourceFlat(simResources.get(0).getId(), simResources.get(0).getSubscriptionId(),
											simResources.get(0).getProviderSubId(),
											simResources.get(0).getProviderId(),
											simResources.get(0).getMSISDN(), simResources.get(0).getIMSI(), simResources.get(0).getICCID(), simResources.get(0).getPIN1(), simResources.get(0).getPIN2(), simResources.get(0).getPUK1(), simResources.get(0).getPUK2(), 
											SimResourceState.GetStringValue(simResources.get(0).getIccidState()),
											SimResourceState.GetStringValue(simResources.get(0).getImsiState()),
											SimResourceState.GetStringValue(simResources.get(0).getMsisdnState()));
		} catch(Exception ex) {
			throw ex;
		} finally {
			logger.info("Execution time: " + TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - start));
		}
		
		return result;
	}
	
	@Transactional
	public SimCardResourceFlat getByMSISDN(Long msisdn) throws SubscriptionResourceNotExistException, SubscriptionResourceFetchException {
		logger.trace("ResourceConfigurationService_Get.getByMSISDN: msisdn = " + msisdn);

		SimCardResourceFlat result = null;
		Long start = System.currentTimeMillis();

		try {
			
			List<SubscriptionSimResourceFlatEntity> simResources = subscriptionSimResourceFlatDao.getByMSISDN(msisdn);
			
			if(simResources.size() == 0)
				throw new SubscriptionResourceNotExistException("ICCID Resource lookup exception: doesn't exist");
			if(simResources.size() != 1)
				throw new SubscriptionResourceFetchException("MSISDN Resource lookup exception: there are more than 1 resource");

			result = new SimCardResourceFlat(simResources.get(0).getId(), simResources.get(0).getSubscriptionId(),
											simResources.get(0).getProviderSubId(),
											simResources.get(0).getProviderId(),
											simResources.get(0).getMSISDN(), simResources.get(0).getIMSI(), simResources.get(0).getICCID(), simResources.get(0).getPIN1(), simResources.get(0).getPIN2(), simResources.get(0).getPUK1(), simResources.get(0).getPUK2(), 
											SimResourceState.GetStringValue(simResources.get(0).getIccidState()),
											SimResourceState.GetStringValue(simResources.get(0).getImsiState()),
											SimResourceState.GetStringValue(simResources.get(0).getMsisdnState()));
		} catch(Exception ex) {
			throw ex;
		} finally {
			logger.info("Execution time: " + TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - start));
		}
		
		return result;
	}
}