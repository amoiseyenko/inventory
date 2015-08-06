package com.aspiderngi.inventory.service;

import java.util.concurrent.TimeUnit;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.common.service.entity.ResourceConfiguration;
import com.aspiderngi.inventory.db.dao.ResourceConfigurationDaoImpl;
import com.aspiderngi.inventory.db.dao.SimResourceICCIDDaoImpl;
import com.aspiderngi.inventory.db.dao.SimResourceIMSIDaoImpl;
import com.aspiderngi.inventory.db.dao.SimResourceMSISDNDaoImpl;
import com.aspiderngi.inventory.db.dao.SimResourceStateDaoImpl;
import com.aspiderngi.inventory.db.entity.ResourceConfigurationEntity;
import com.aspiderngi.inventory.db.entity.ResourceConfigurationStateEntity;
import com.aspiderngi.inventory.db.entity.ResourceICCIDEntity;
import com.aspiderngi.inventory.db.entity.ResourceIMSIEntity;
import com.aspiderngi.inventory.db.entity.ResourceMSISDNEntity;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceExistException;
import com.aspiderngi.inventory.service.entity.enums.SimResourceState;

@Component
public class ResourceConfigurationService_Import {
	
	@Autowired
	SimResourceStateDaoImpl simResourceStateDao;
	
	@Autowired
	SimResourceICCIDDaoImpl simResourceICCIDDao;
	
	@Autowired
	SimResourceIMSIDaoImpl simResourceIMSIDao;
	
	@Autowired
	SimResourceMSISDNDaoImpl simResourceMSISDNDao;
	
	@Autowired
	ResourceConfigurationDaoImpl subscriptionSimResourceDao;

	private static final Logger logger = LoggerFactory.getLogger(ResourceConfigurationService_Import.class);

	public ResourceConfigurationService_Import() {
		logger.debug("ResourceConfigurationService_Import.ctor");
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor = { SubscriptionResourceExistException.class })
	public Long importResourceConfiguration(ResourceConfiguration resource) throws SubscriptionResourceExistException {
		logger.trace("SimResourceService_Import.importSimResource");
		
		Long id = -1L;
		Long start = System.currentTimeMillis();

		try {
			ResourceConfigurationStateEntity srse = this.simResourceStateDao.getByName(SimResourceState.PREACTIVE.toString());

			ResourceConfigurationEntity entity = new ResourceConfigurationEntity();
			entity.getIccids().add(new ResourceICCIDEntity(resource.getICCID(), resource.getPIN1(), resource.getPIN2(), resource.getPUK1(), resource.getPUK2(), entity, srse));
			entity.getImsis().add(new ResourceIMSIEntity(resource.getIMSI(), entity, srse));
			entity.getMsisdns().add(new ResourceMSISDNEntity(resource.getMSISDN(), entity, srse));

			id = subscriptionSimResourceDao.create(entity);
			
			if (null != entity.getIccids() && !entity.getIccids().isEmpty()) {
				for (ResourceICCIDEntity iccidEntity : entity.getIccids()) {
					simResourceICCIDDao.create(iccidEntity);
				}
			}
			
			if(null != entity.getImsis() && !entity.getImsis().isEmpty()) {
				for (ResourceIMSIEntity imsiEntity : entity.getImsis()) {
					simResourceIMSIDao.create(imsiEntity);
				}
			}

			if(null != entity.getMsisdns() && !entity.getMsisdns().isEmpty()) {
				for (ResourceMSISDNEntity msisdnEntity : entity.getMsisdns()) {
					simResourceMSISDNDao.create(msisdnEntity);
				}
			}
		} catch(ConstraintViolationException ex) {
			throw new SubscriptionResourceExistException("Resource already exists: " + resource.toString());
		} finally {
			logger.info("Execution time: " + TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - start));
		}

		return id;
	}
}