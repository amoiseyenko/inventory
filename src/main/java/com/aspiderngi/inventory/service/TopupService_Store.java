package com.aspiderngi.inventory.service;

import java.text.MessageFormat;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aspiderngi.common.service.entity.Topup;
import com.aspiderngi.common.service.entity.enums.TopupState;
import com.aspiderngi.inventory.db.dao.CustomerDaoImpl;
import com.aspiderngi.inventory.db.dao.TopupDaoImpl;
import com.aspiderngi.inventory.db.dao.TopupStateDaoImpl;
import com.aspiderngi.inventory.db.dao.TopupTypeDaoImpl;
import com.aspiderngi.inventory.db.entity.CustomerEntity;
import com.aspiderngi.inventory.db.entity.TopupEntity;
import com.aspiderngi.inventory.db.entity.TopupStateEntity;
import com.aspiderngi.inventory.db.entity.TopupTypeEntity;
import com.aspiderngi.inventory.db.exception.CustomerNotFoundException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.exception.TopupStateNotFoundException;
import com.aspiderngi.inventory.db.utils.PopulationUtils;

@Component
public class TopupService_Store {
 
	private static final Logger logger = LoggerFactory.getLogger(TopupService_Store.class);

	@Autowired
	private TopupDaoImpl topupDao;

	@Autowired
	private TopupStateDaoImpl topupStateDao;
	
	@Autowired
	private TopupTypeDaoImpl topupTypeDao;

	@Autowired
	private CustomerDaoImpl customerDao;
 
	@Transactional
	public Long storeTopup(Topup topup) throws ServerGenericException {
		Long id = -1L;
		
		try{
			CustomerEntity customerEntity = customerDao.read(topup.getCustomerId());
			if(customerEntity == null) {
				logger.error("Unable to fund customer with id {}",topup.getCustomerId());
				throw new CustomerNotFoundException(MessageFormat.format("Unable to find customer with id {0}", topup.getCustomerId()));
			}
			
			
			TopupStateEntity topupState;			
			String name;
			TopupTypeEntity topupType = topupTypeDao.getTypeByName(topup.getTopUpType().name());
			
			if (topup.getStatus() != null) {
				name = topup.getStatus().name();
			} else {
				name = TopupState.INIT.name();
			}
			
			topupState = topupStateDao.getStateByName(name);
			TopupEntity entity = PopulationUtils.populateTopupEntity(topup);
			if(topupState == null){
				logger.error("Unable to find Topup State {}",name);
				throw new TopupStateNotFoundException(MessageFormat.format("Unable to find topupState: {0}", name));
			}

			entity.setCustomer(customerEntity);
			entity.setTopupState(topupState);
			entity.setTopupType(topupType);

			id = topupDao.create(entity);
		} catch(Exception exc) {
			logger.error("{}", exc);
			throw new ServerGenericException(exc.getMessage());
		}
		
		return id;
	}

}