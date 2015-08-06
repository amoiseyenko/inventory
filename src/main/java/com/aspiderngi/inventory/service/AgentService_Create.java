package com.aspiderngi.inventory.service;


import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.common.service.entity.AgentDetails;
import com.aspiderngi.common.service.entity.AgentRegistrationInfo;
import com.aspiderngi.inventory.db.dao.AgentDaoImpl;
import com.aspiderngi.inventory.db.dao.RoleDaoImpl;
import com.aspiderngi.inventory.db.entity.AgentEntity;
import com.aspiderngi.inventory.db.entity.RoleEntity;
import com.aspiderngi.inventory.db.exception.LoginAlreadyRegisteredException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.utils.PopulationUtils;
import com.aspiderngi.inventory.service.entity.enums.RolesState;

@Service
public class AgentService_Create {

	Logger logger = LoggerFactory.getLogger(AgentService_Create.class);
	
	@Autowired
	AgentDaoImpl agentDao;
	
	@Autowired
	RoleDaoImpl roleDao;
	
	StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Long createAgent(AgentRegistrationInfo agent) throws ServerGenericException, LoginAlreadyRegisteredException{
		Long idLong = -1L;
		try{
			agent.setPassword(passwordEncoder.encode(agent.getPassword()));
			AgentEntity agentEntity = PopulationUtils.populateAgentEntity(agent);
			RoleEntity roleEntity = roleDao.read(new Long(RolesState.AGENT.getId()));
			agentEntity.setRole(roleEntity);
			idLong = agentDao.create(agentEntity);
		}
		catch(ConstraintViolationException exception ){
			logger.error("{}",exception);  
			throw new LoginAlreadyRegisteredException();
		}
		catch(Exception exc){
			logger.error("{}",exc);
			throw new ServerGenericException();
		}
		return idLong;
	}
	
}
