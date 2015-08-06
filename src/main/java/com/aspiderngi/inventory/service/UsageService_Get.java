package com.aspiderngi.inventory.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aspiderngi.common.service.entity.UsageDetails;
import com.aspiderngi.common.service.entity.enums.UsageType;
import com.aspiderngi.inventory.db.dao.UsageDaoImpl;
import com.aspiderngi.inventory.db.entity.UsageEntity;
import com.aspiderngi.inventory.db.exception.ServerGenericException;

@Component
public class UsageService_Get {

	private static final Logger logger = LoggerFactory.getLogger(UsageService_Get.class);
	
	@Autowired
	private UsageDaoImpl usageDao;
	
	@Transactional
	public ArrayList<UsageDetails> get(Long subscriptionId, Long position, Integer count) throws ServerGenericException {
		ArrayList<UsageDetails> result = new ArrayList<UsageDetails>();

		try {
			ArrayList<UsageEntity> usageEntities = usageDao.get(subscriptionId, position, count);
			for(UsageEntity ue : usageEntities) {
				UsageDetails details = new UsageDetails(ue.getUsageDate(), 
														UsageType.GetValue(ue.getUsageType()), 
														ue.getExtra(),
														((double)ue.getUsageValue()/100.0),	// in seconds.miliseconds. so SMS value is 60, that means 1 SMS
														ue.getUsageCost(),					// cost has pecision of 1000.
														ue.getPosition());

				result.add(details);
			}
		} catch(Exception exc) {
			logger.error("{}", exc);
			throw new ServerGenericException(exc.getMessage());
		}
		
		return result;
	}	
}