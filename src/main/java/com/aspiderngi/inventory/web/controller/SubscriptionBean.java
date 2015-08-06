package com.aspiderngi.inventory.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.aspiderngi.artifacts.model.SuspendRequestModel;
import com.aspiderngi.common.service.entity.Subscription;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.db.exception.ResourceInconsistentStateException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.exception.SubscriptionAlreadyExistException;
import com.aspiderngi.inventory.db.exception.SubscriptionInconsistentStateException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceExistException;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceFetchException;
import com.aspiderngi.inventory.db.exception.SubscriptionStateNotFoundException;
import com.aspiderngi.inventory.service.SubscriptionService_Activate;
import com.aspiderngi.inventory.service.SubscriptionService_Create;
import com.aspiderngi.inventory.service.SubscriptionService_Get;
import com.aspiderngi.inventory.service.SubscriptionService_Terminate;
import com.aspiderngi.inventory.service.SubscriptionSuspend_Update;

@Component
public class SubscriptionBean implements SubscriptionController {

	private static final Logger logger = LoggerFactory.getLogger(SubscriptionBean.class);

	@Autowired
	SubscriptionService_Create createSubscriptionService;
	
	@Autowired
	SubscriptionService_Activate activateSubscriptionService;

	@Autowired
	SubscriptionService_Get getSubscriptionService;
	
	@Autowired 
	SubscriptionService_Terminate subscriptionServiceTerminate;
	
	@Autowired 
	SubscriptionSuspend_Update subscriptionSuspendUpdate;

	@Override
	public OperationResultParam<Long> createSubscription(@RequestBody Subscription subscription) {
		logger.trace("SubscriptionServiceBean.createSubscription: Subscription = " + subscription.toString());

		Long subscriptionId = -1L;
		try {
			subscriptionId = createSubscriptionService.createSubscription(subscription);
		} catch (SubscriptionAlreadyExistException e) {
			return new OperationResultParam<Long>(e.EXCEPTION_CODE, e.getMessage(), -1L);
		} catch (SubscriptionResourceFetchException e) {
			return new OperationResultParam<Long>(e.EXCEPTION_CODE, e.getMessage(), -1L);
		} catch (ResourceInconsistentStateException e) {
			return new OperationResultParam<Long>(e.EXCEPTION_CODE, e.getMessage(), -1L);
		} catch (SubscriptionResourceExistException e) {
			return new OperationResultParam<Long>(e.EXCEPTION_CODE, e.getMessage(), -1L);
		}

		return new OperationResultParam<Long>("OK", "OK", subscriptionId);
	}

	@Override
	public OperationResult activateSubscription(@PathVariable String subscriptionId, @PathVariable String spName) {
		logger.trace("SubscriptionServiceBean.activateSubscription: SubscriptionID = " + subscriptionId + " for provider '" + spName + "'");
		
		try {
			activateSubscriptionService.activateSubscription(subscriptionId, spName);
		} catch (SubscriptionInconsistentStateException e) {
			return new OperationResult(e.EXCEPTION_CODE, e.getMessage());
		} catch (SubscriptionNotFoundException e) {
			return new OperationResult(e.EXCEPTION_CODE, e.getMessage());
		}

		return new OperationResult("OK", "OK");
	}	
	
	@Override
	public OperationResultParam<Subscription> getSubscription(@PathVariable String subscriptionId, @PathVariable String spName) {
		logger.trace("SubscriptionServiceBean.activateSubscription: SubscriptionID = " + subscriptionId + " for provider '" + spName + "'");

		Subscription subscription;
		try {
			subscription = getSubscriptionService.getSubscription(subscriptionId, spName);
		} catch (SubscriptionInconsistentStateException e) {
			return new OperationResultParam<Subscription>(e.EXCEPTION_CODE, e.getMessage());
		} catch (SubscriptionNotFoundException e) {
			return new OperationResultParam<Subscription>(e.EXCEPTION_CODE, e.getMessage());
		}

		return new OperationResultParam<Subscription>("OK", "OK", subscription);
	}
	
	@Override
	public OperationResult terminateSubscription(@PathVariable String subscriptionId, @PathVariable String spName) {
		logger.info("Terminating subscription {}", subscriptionId);
		
		try {
			subscriptionServiceTerminate.terminateSubscription(subscriptionId, spName);
		} catch(SubscriptionNotFoundException exc) {
			logger.error("{}",exc);
			return new OperationResult(SubscriptionNotFoundException.EXCEPTION_CODE, exc.getMessage());
		} catch(SubscriptionStateNotFoundException exc) {
			logger.error("{}",exc);
			return new OperationResult(SubscriptionStateNotFoundException.EXCEPTION_CODE, exc.getMessage());
		} catch(ServerGenericException exc) {
			logger.error("{}",exc);
			return new OperationResult(exc.EXCEPTION_CODE, exc.getMessage());
		}
		
		return new OperationResult("OK", "OK");
	}

	@Override
	public OperationResult updateSuspendState(@PathVariable String subscriptionId, @PathVariable String spName, @RequestBody SuspendRequestModel suspendRequest) {
		logger.info("Update subscription Suspend State: {}, SPName={}, SuspendStates={}", subscriptionId, spName, suspendRequest);

		try {
			subscriptionSuspendUpdate.updateSuspendState(subscriptionId, spName, suspendRequest);
		} catch(SubscriptionNotFoundException exc) {
			logger.error("{}",exc);
			return new OperationResult(SubscriptionNotFoundException.EXCEPTION_CODE, exc.getMessage());
		} catch(SubscriptionStateNotFoundException exc) {
			logger.error("{}",exc);
			return new OperationResult(SubscriptionStateNotFoundException.EXCEPTION_CODE, exc.getMessage());
		} catch(ServerGenericException exc) {
			logger.error("{}",exc);
			return new OperationResult(exc.EXCEPTION_CODE, exc.getMessage());
		}

		return new OperationResult("OK", "OK");
	}

}