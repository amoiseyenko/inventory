package com.aspiderngi.inventory.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aspiderngi.common.service.entity.Topup;
import com.aspiderngi.inventory.db.dao.TopupDaoImpl;
import com.aspiderngi.inventory.db.entity.TopupEntity;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.utils.PopulationUtils;

@Component
public class TopupService_Get {

	private static final Logger logger = LoggerFactory.getLogger(TopupService_Get.class);
	
	@Autowired
	private TopupDaoImpl topupDao;
	
	@Transactional
	public ArrayList<Topup> getTopupHishoryForCustomer(Long customerId, Integer count) throws ServerGenericException{
		ArrayList<Topup> aList = null;
		
		try{
			ArrayList<TopupEntity> entityList = topupDao.getSuccessfulTopupsByCustomerId(customerId, count);
			aList = PopulationUtils.populateTopupArray(entityList);
		} catch(Exception exc) {
			logger.error("{}", exc);
			throw new ServerGenericException(exc.getMessage());
		}
		
		return aList;
	}
	
	@Transactional
	public int getSuccessfulCount(Long customerId, int limit) throws ServerGenericException{
	
		try{
			ArrayList<TopupEntity> entityList = topupDao.getNumberOfSuccessfulIdealTopups(customerId, limit);
			
			return entityList.size();
		} catch(Exception exc) {
			logger.error("{}", exc);
			throw new ServerGenericException(exc.getMessage());
		}
	}	
}