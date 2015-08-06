package com.aspiderngi.inventory.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspiderngi.common.service.entity.Agent;
import com.aspiderngi.inventory.db.dao.AgentDaoImpl;
import com.aspiderngi.inventory.db.entity.AgentEntity;
import com.aspiderngi.inventory.db.exception.AgentNotFoundException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.utils.PopulationUtils;

@Service
public class AgentService_Get {

	Logger logger = LoggerFactory.getLogger(AgentService_Get.class);
	
	@Autowired
	AgentDaoImpl agentDao;
	
	@Transactional
	public Agent getByLogin(String login) throws ServerGenericException,AgentNotFoundException{
		logger.info("Getting agent by email:{}",login);
		Agent agent = null;
		try{
			AgentEntity entity = agentDao.getByLogin(login);
			if(entity == null){
				logger.info("Unable to find agent by email {}",login);
				throw new AgentNotFoundException();
			}
			agent = PopulationUtils.populateAgent(entity);
			
		}catch(Exception exc){
			logger.error("{}",exc);
			throw new ServerGenericException(exc.getMessage());
		}
		return agent;
	}
	
}
