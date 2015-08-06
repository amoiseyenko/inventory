package com.aspiderngi.inventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aspiderngi.common.service.entity.PasswordChange;
import com.aspiderngi.inventory.db.dao.PasswordManagementDaoImpl;
import com.aspiderngi.inventory.db.entity.PasswordMgmtEntity;
import com.aspiderngi.inventory.db.exception.ForgotPasswordTokenNotFoundException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;

@Component
public class ForgotPasswordTokenService_Get {
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordTokenService_Get.class);
	
	@Autowired
	private PasswordManagementDaoImpl passwordMaangementDao;
	
	@Transactional
	public PasswordChange getForgotPasswordToken(Long customerId, String token) throws ServerGenericException {
		PasswordMgmtEntity entity = null;
		PasswordChange passwordChange = null;
		
		try {
			entity = passwordMaangementDao.getForgotPasswordToken(customerId, token);
			if(entity == null) {
				throw new Exception("There is no token in DB");
			}
			passwordChange = new PasswordChange();
			passwordChange.setActivationDate(entity.getActivationDate());
			passwordChange.setToken(entity.getToken());			
			passwordChange.setCustomerId(entity.getCustomer().getId());
			passwordChange.setId(entity.getId());
		} catch(Exception exc){
			logger.error("Unable to find token = {} for customer ID = ", token, customerId);
			throw new ServerGenericException("Cannot find token");
		}
		return passwordChange;
	}
	
	@Transactional
	public PasswordChange getForgotPasswordTokenByCustomerId(Long customerId) throws ForgotPasswordTokenNotFoundException {
		PasswordMgmtEntity entity = null;
		PasswordChange passwordChange = null;
		
		try {
			entity = passwordMaangementDao.getForgotPasswordTokenByCustomerId(customerId);
			if(entity == null) {
				throw new Exception("There is no token in DB");
			}
			passwordChange = new PasswordChange();
			passwordChange.setActivationDate(entity.getActivationDate());
			passwordChange.setToken(entity.getToken());			
			passwordChange.setCustomerId(entity.getCustomer().getId());
			passwordChange.setId(entity.getId());
		} catch(Exception exc){
			logger.error("Unable to find any token for customer ID = ", customerId);
			throw new ForgotPasswordTokenNotFoundException("NO_TOKEN_AVAILABLE");
		}
		return passwordChange;
	}
}