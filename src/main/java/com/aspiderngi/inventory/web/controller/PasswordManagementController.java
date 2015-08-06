package com.aspiderngi.inventory.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aspiderngi.common.service.entity.ForgotPasswordToken;
import com.aspiderngi.common.service.entity.PasswordChange;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;

@RestController
@RequestMapping("/pwd-mgmt")
public interface PasswordManagementController {
	@RequestMapping(value = "/{customerId}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResult postForgotPasswordToken(@PathVariable Long customerId, @RequestBody ForgotPasswordToken forgotPasswordToken);
	
	@RequestMapping(value = "/{customerId}/{token}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<PasswordChange> getForgotPasswordToken(@PathVariable Long customerId, @PathVariable String token);

	@RequestMapping(value = "/{customerId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	OperationResultParam<PasswordChange> getForgotPasswordTokenByCustomerId(@PathVariable Long customerId);

	@RequestMapping(value = "/{tokenId}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	OperationResult updateForgotPasswordTokenActDate(@PathVariable Long tokenId);
}
