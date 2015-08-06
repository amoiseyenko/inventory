package com.aspiderngi.inventory.web.controller.agent;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aspiderngi.common.service.entity.Agent;
import com.aspiderngi.common.service.entity.AgentDetails;
import com.aspiderngi.common.service.entity.AgentRegistrationInfo;
import com.aspiderngi.common.service.entity.CustomerRegistrationInfo;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;

@RestController
@RequestMapping("/agent")
public interface AgentController {
	 
	@RequestMapping(value = "",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			params = {"login"})
	public @ResponseBody OperationResultParam<Agent>  getByLogin(
			@RequestParam(value = "login", required = true, defaultValue="") String login);
 
	@RequestMapping(value = "",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResult create(@RequestBody AgentRegistrationInfo agent);
}