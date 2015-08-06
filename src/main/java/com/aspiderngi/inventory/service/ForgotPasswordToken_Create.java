package com.aspiderngi.inventory.service;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.common.service.entity.ForgotPasswordToken;
import com.aspiderngi.inventory.db.dao.CustomerDaoImpl;
import com.aspiderngi.inventory.db.dao.PasswordManagementDaoImpl;
import com.aspiderngi.inventory.db.entity.CustomerEntity;
import com.aspiderngi.inventory.db.entity.PasswordMgmtEntity;
import com.aspiderngi.inventory.db.exception.ServerGenericException;

@Component
public class ForgotPasswordToken_Create {
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordToken_Create.class);
	
	@Autowired
	private CustomerDaoImpl customerDao;
	
	@Autowired
	private PasswordManagementDaoImpl passwordMaangementDao;
	
	@Transactional
	public void createForgotPasswordToken(Long customerId, ForgotPasswordToken forgotPasswordToken) throws ServerGenericException {
		PasswordMgmtEntity entity = null;
		
		try{
			CustomerEntity customer = customerDao.read(customerId);
			entity = new PasswordMgmtEntity();
			entity.setToken(forgotPasswordToken.getToken());
			entity.setRequestedDate(forgotPasswordToken.getDateRequested());
			entity.setCustomer(customer);
			passwordMaangementDao.create(entity);
		} catch(Exception exc) {
			logger.error("{}", exc);
			throw new ServerGenericException(MessageFormat.format("Error creating forgot password token for customer id = {0}, value = {1}", customerId, entity));
		}
	}
}
