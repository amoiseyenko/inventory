package com.aspiderngi.inventory.web.controller.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspiderngi.common.service.entity.Agent;
import com.aspiderngi.common.service.entity.AgentDetails;
import com.aspiderngi.common.service.entity.AgentRegistrationInfo;
import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.db.exception.AgentNotFoundException;
import com.aspiderngi.inventory.db.exception.LoginAlreadyRegisteredException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.service.AgentService_Create;
import com.aspiderngi.inventory.service.AgentService_Get;

@Component
public class AgentBean implements AgentController {

	@Autowired
	AgentService_Get agentServiceGet;
	
	@Autowired
	AgentService_Create agentServiceCreate;
	
	
	Logger logger = LoggerFactory.getLogger(AgentBean.class);
	
	@Override
	public OperationResultParam<Agent> getByLogin(@RequestParam String login) {
		Agent agentDetails = null;
		try{
			agentDetails = agentServiceGet.getByLogin(login);
		}catch(AgentNotFoundException exc){
			return new OperationResultParam<Agent>("Agent not found","ERROR",null);
		}catch(ServerGenericException exc){
			return new OperationResultParam<Agent>("Internal Server Error","ERROR",null);
		}
		return new OperationResultParam<Agent>("OK","OK",agentDetails);
	}
	
	@Override
	public @ResponseBody OperationResultParam<Long> create(@RequestBody AgentRegistrationInfo agentInfo){
		Long agentId;
		try{
			 agentId = agentServiceCreate.createAgent(agentInfo);
			logger.info("Agent created with id {}",agentId);
		}catch(LoginAlreadyRegisteredException exc){
			return new OperationResultParam<Long>("ERROR","Login already registered",null);
		}
		catch(ServerGenericException exc){
			return new OperationResultParam<Long>("ERROR","ERROR",null);
		}
		return new OperationResultParam<Long>("OK","OK",agentId);
	}
}
