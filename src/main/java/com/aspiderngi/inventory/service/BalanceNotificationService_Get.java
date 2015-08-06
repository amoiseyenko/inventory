package com.aspiderngi.inventory.service;

import java.text.MessageFormat;
import java.util.List;

import com.aspiderngi.common.service.entity.LowBalanceNotification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.aspiderngi.inventory.db.dao.CustomerSettingsDaoImpl;
import com.aspiderngi.inventory.db.entity.CustomerSettingsEntity;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.common.service.entity.LowBalanceNotification;
import com.aspiderngi.common.service.util.SettingsNames;

@Component
public class BalanceNotificationService_Get {

	private static final Logger logger = LoggerFactory.getLogger(BalanceNotificationService_Get.class);
	
	@Autowired
	CustomerSettingsDaoImpl settingsDao;
	
	@Transactional
	public LowBalanceNotification getLowBalanceNotificationSettings(Long customerId) throws ServerGenericException {
		LowBalanceNotification lowBalanceNotification = null;
		
		try{
			List<CustomerSettingsEntity> settings = settingsDao.getSettingsByCustomerId(customerId);
			CustomerSettingsEntity notificationSetting = null;
			for (int i=0; i<settings.size(); i++)
				if (settings.get(i).getName().equals(SettingsNames.LOWBALANCENOTIFICATION)) {
					notificationSetting = settings.get(i);
					break;
				}
			
			if (notificationSetting == null) {
				throw new Exception("There is no low balance notification setting for this user.");
			}
			
			lowBalanceNotification = new LowBalanceNotification(Boolean.parseBoolean(notificationSetting.getValue()));
		} catch(Exception exc) {
			logger.error("Unable to find user settings for customer id = {}", customerId);
			throw new ServerGenericException(MessageFormat.format("Cannot find user settings for customer id = {0}", customerId));
		}
		
		return lowBalanceNotification;
	}
}