package com.aspiderngi.inventory.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aspiderngi.common.service.entity.Topup;
import com.aspiderngi.common.service.entity.enums.TopupState;
import com.aspiderngi.inventory.db.dao.TopupDaoImpl;
import com.aspiderngi.inventory.db.dao.TopupStateDaoImpl;
import com.aspiderngi.inventory.db.entity.TopupEntity;
import com.aspiderngi.inventory.db.entity.TopupStateEntity;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.utils.PopulationUtils;

@Component
public class TopupService_Update {

	private static final Logger logger = LoggerFactory.getLogger(TopupService_Update.class);
	
	@Autowired
	private TopupDaoImpl topupDao;
	
	@Autowired
	private TopupStateDaoImpl topupStateDao;
	
	@Transactional
	public Topup updateTopup(String transactionId, TopupState state) throws ServerGenericException {
		
		try {
			TopupStateEntity topupState = topupStateDao.getStateByName(state.name());
			TopupEntity topUpEnt = topupDao.getTopupByTransactionId(transactionId);
			Topup topUp = null;
			
			topUpEnt.setTopupState(topupState); 
			
			topupDao.update(topUpEnt);
			
			topUp = PopulationUtils.populateTopup(topUpEnt);
			
			return topUp;
		} catch(Exception exc) {
			logger.error("{}", exc);
			
			throw new ServerGenericException(exc.getMessage());
		}
	}
}