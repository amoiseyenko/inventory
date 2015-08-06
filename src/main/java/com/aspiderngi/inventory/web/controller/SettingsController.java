package com.aspiderngi.inventory.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aspiderngi.common.service.entity.LowBalanceNotification;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;

@RestController
@RequestMapping("/settings")
public interface SettingsController {
	@RequestMapping(value = "/{customerId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<LowBalanceNotification> getLowBalanceNotification(@PathVariable Long customerId);
	
	@RequestMapping(value = "/{customerId}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResult put(@PathVariable Long customerId, @RequestBody LowBalanceNotification lowBalanceNotification);
}
