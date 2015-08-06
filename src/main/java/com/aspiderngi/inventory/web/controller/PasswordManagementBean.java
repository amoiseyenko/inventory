package com.aspiderngi.inventory.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.aspiderngi.common.service.entity.CustomerConfirmationInfo;
import com.aspiderngi.common.service.entity.ForgotPasswordToken;
import com.aspiderngi.common.service.entity.PasswordChange;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.db.exception.ForgotPasswordTokenNotFoundException;
import com.aspiderngi.inventory.service.ForgotPasswordTokenService_Get;
import com.aspiderngi.inventory.service.ForgotPasswordTokenService_Update;
import com.aspiderngi.inventory.service.ForgotPasswordToken_Create;

@Component
public class PasswordManagementBean implements PasswordManagementController {

private static final Logger logger = LoggerFactory.getLogger(PasswordManagementBean.class);
	
	@Autowired
	private ForgotPasswordToken_Create forgotPasswordTokenCreate;
	
	@Autowired
	private ForgotPasswordTokenService_Get forgotPasswordTokenGet;
	
	@Autowired
	private ForgotPasswordTokenService_Update forgotPasswordTokenUpdate;
	
	@Override
	public OperationResult postForgotPasswordToken(@PathVariable Long customerId, @RequestBody ForgotPasswordToken forgotPasswordToken) {
		logger.info("Get low balance notification for customer id: {}", customerId);
		
		try {
			forgotPasswordTokenCreate.createForgotPasswordToken(customerId, forgotPasswordToken);		
		} catch(Exception ex) {
			logger.error("{}", ex);
			return new OperationResult("ERROR", ex.getMessage());
		}				
		return new OperationResult("OK", "OK");
	}

	@Override
	public OperationResultParam<PasswordChange> getForgotPasswordToken(@PathVariable Long customerId, @PathVariable String token) {
		logger.info("Get forgot password token: {} for customer id: {}", token, customerId);
		
		PasswordChange passChange;
		
		try {
			passChange = forgotPasswordTokenGet.getForgotPasswordToken(customerId, token);
		} catch (Exception ex) {
			logger.error("Forgot Password token: {} not found for customer id: {}", token, customerId);
			return new OperationResultParam<PasswordChange>("ERROR", ex.getMessage(), null);
		}
		
		return new OperationResultParam<PasswordChange>("OK", "OK", passChange);
	}
	
	@Override
	public OperationResultParam<PasswordChange> getForgotPasswordTokenByCustomerId(@PathVariable Long customerId) {
		logger.info("Get forgot password token for customer id: {}", customerId);
		
		PasswordChange passChange;
		
		try {
			passChange = forgotPasswordTokenGet.getForgotPasswordTokenByCustomerId(customerId);
		} catch (ForgotPasswordTokenNotFoundException ex) {
			logger.error("No forgot password token found for customer id: {}", customerId);
			return new OperationResultParam<PasswordChange>(ex.EXCEPTION_CODE, ex.getMessage(), null);
		} catch (Exception ex) {
			logger.error("No forgot password token found for customer id: {}", customerId);
			return new OperationResultParam<PasswordChange>("ERROR", ex.getMessage(), null);
		}
		
		return new OperationResultParam<PasswordChange>("OK", "OK", passChange);
	}
	
	@Override
	public OperationResult updateForgotPasswordTokenActDate(@PathVariable Long tokenId) {
		logger.info("Update activation date for forgot password token id: {}", tokenId);
		
		try {
			forgotPasswordTokenUpdate.updateForgotPassTokenActDate(tokenId);
		} catch (Exception ex) {
			logger.error("Error updating activation date for forgot password token id: {}", tokenId);
			return new OperationResult("ERROR", ex.getMessage());
		}
		
		return new OperationResult("OK", "OK");
	}
}