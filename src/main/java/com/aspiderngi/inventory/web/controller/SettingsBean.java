package com.aspiderngi.inventory.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.aspiderngi.common.service.entity.LowBalanceNotification;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.service.BalanceNotificationService_Get;
import com.aspiderngi.inventory.service.BalanceNotificationService_Set;

@Component
public class SettingsBean implements SettingsController {

	private static final Logger logger = LoggerFactory.getLogger(SettingsBean.class);
	
	@Autowired
	private BalanceNotificationService_Get balanceNotificationServiceGet;

	@Autowired
	private BalanceNotificationService_Set balanceNotificationServiceSet;
	
	@Override
	public OperationResultParam<LowBalanceNotification> getLowBalanceNotification(@PathVariable Long customerId) {
		logger.info("Get low balance notification for customer id: {}", customerId);
		
		LowBalanceNotification lowBalanceNotification = new LowBalanceNotification();
		try {
			lowBalanceNotification = balanceNotificationServiceGet.getLowBalanceNotificationSettings(customerId);		
		} catch(Exception ex) {
			logger.error("{}", ex);
			return new OperationResultParam<LowBalanceNotification>("ERROR", ex.getMessage(), null);
		}				
		return new OperationResultParam<LowBalanceNotification>("OK", "OK", lowBalanceNotification);
	}

	@Override
	public OperationResult put(@PathVariable Long customerId, @RequestBody LowBalanceNotification lowBalanceNotification) {
		logger.info("Update low balance notification for customer id: {0}, setting low balance notification: {1}", customerId, lowBalanceNotification.getSendSMS());
		
		try {
			balanceNotificationServiceSet.updateLowBalanceNotificationSettings(customerId, lowBalanceNotification);
		} catch (Exception ex) {
			logger.error("{}", ex);
			return new OperationResult("INVALID_REQUEST_ARGUMENTS", ex.getMessage());
		}
		return new OperationResult("OK", "OK");
	}	
}
