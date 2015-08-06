package com.aspiderngi.inventory.service;


import java.text.MessageFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.inventory.db.dao.PasswordManagementDaoImpl;
import com.aspiderngi.inventory.db.entity.PasswordMgmtEntity;
import com.aspiderngi.inventory.db.exception.ServerGenericException;

@Component
public class ForgotPasswordTokenService_Update {
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordTokenService_Update.class);
	
	@Autowired
	private PasswordManagementDaoImpl passwordManagementDao;
	
	@Transactional
	public void updateForgotPassTokenActDate(Long tokenId) throws ServerGenericException {
		try {
			PasswordMgmtEntity entity = passwordManagementDao.read(tokenId);
			entity.setActivationDate(new Date());
			passwordManagementDao.update(entity);
		} catch(Exception ex) {
			logger.error("{}", ex);
			throw new ServerGenericException(MessageFormat.format("Error updating activation date for forgot password token id={}", tokenId));
		}
	}
}