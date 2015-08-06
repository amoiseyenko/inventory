package com.aspiderngi.inventory.web.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspiderngi.common.service.entity.UsageDetails;
import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.service.UsageService_Get;

@Component
public class UsageBean implements UsageController {

	private static final Logger logger = LoggerFactory.getLogger(UsageBean.class);
	
	@Autowired
	private UsageService_Get usageService;
	
	@Override
	public OperationResultParam<ArrayList<UsageDetails>> getUsage(
			@PathVariable Long subscriptionId,
			@RequestParam(value = "position", required = false, defaultValue="0") Long position,
			@RequestParam(value = "count", required = false, defaultValue="30") Integer count) {

		logger.info("Getting usage for {} at position {}. Count = {}", subscriptionId, position, count);

		ArrayList<UsageDetails> result = null;
		
		try {
			result = usageService.get(subscriptionId, position, count);
		} catch (ServerGenericException e) {
			logger.error(e.getMessage());
			
			return new OperationResultParam<ArrayList<UsageDetails>>("ERROR", e.getMessage(), result);
		}

		return new OperationResultParam<ArrayList<UsageDetails>>("OK", "OK", result);
	}
}