package com.aspiderngi.inventory.service;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.common.service.entity.LowBalanceNotification;
import com.aspiderngi.common.service.util.SettingsNames;
import com.aspiderngi.inventory.db.dao.CustomerSettingsDaoImpl;
import com.aspiderngi.inventory.db.entity.CustomerSettingsEntity;
import com.aspiderngi.inventory.db.exception.ServerGenericException;

@Component
public class BalanceNotificationService_Set {

	private static final Logger logger = LoggerFactory.getLogger(BalanceNotificationService_Set.class);
	
	@Autowired
	CustomerSettingsDaoImpl settingsDao;
	
	@Transactional
	public void updateLowBalanceNotificationSettings(Long customerId, LowBalanceNotification lowBalanceNotification) throws ServerGenericException {
				
		try{			
			List<CustomerSettingsEntity> settings = settingsDao.getSettingsByCustomerId(customerId);
			CustomerSettingsEntity notificationSetting = null;
			for (int i=0; i<settings.size(); i++) {
				if (settings.get(i).getName().equals(SettingsNames.LOWBALANCENOTIFICATION)) {
					notificationSetting = settings.get(i);
					break;
				}
			}
			
			if (notificationSetting != null) {
				notificationSetting.setValue(lowBalanceNotification.getSendSMS().toString());
				settingsDao.update(notificationSetting);
			} else {
				throw new Exception("There is no low balance notification setting for this user.");
			}
		} catch(Exception exc) {
			logger.error("{}", exc);
			throw new ServerGenericException(MessageFormat.format("Error updating customer settings with customer id = {0}, value = {1}", customerId, lowBalanceNotification.getSendSMS()));
		}
	}
}