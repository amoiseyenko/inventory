package com.aspiderngi.inventory.web.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.aspiderngi.common.service.entity.Topup;
import com.aspiderngi.common.service.entity.enums.TopupState;
import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.service.TopupService_Get;
import com.aspiderngi.inventory.service.TopupService_Store;
import com.aspiderngi.inventory.service.TopupService_Update;

@Component
public class TopupBean implements TopupController {

	private static final Logger logger = LoggerFactory.getLogger(TopupBean.class);

	@Autowired
	private TopupService_Store topupServiceStore;
	
	@Autowired
	private TopupService_Get topupServiceGet;

	@Autowired
	private TopupService_Update topupServiceUpdate;

	@Override
	public OperationResultParam<Long> startTopup(@RequestBody Topup topup) {
		logger.info("Storing topup {}", topup);
		
		Long entityId = -1L;
		try {
			entityId = topupServiceStore.storeTopup(topup);
		} catch(ServerGenericException exc) {
			logger.error("{}", exc);
			return new OperationResultParam<Long>().setResultValue(null);
		}
		
		return new OperationResultParam<Long>("OK", "OK", entityId);
	}

	@Override
	public OperationResultParam<Topup> finishTopup(@PathVariable Integer stateId, @PathVariable String transactionId) {
		logger.info("Finishing topup for transaction {} with stateId={}", transactionId, stateId);
		Topup topup = null;

		try {
			topup = topupServiceUpdate.updateTopup(transactionId, TopupState.getValue(stateId));
		} catch(ServerGenericException exc) {
			logger.error("{}", exc);

			return new OperationResultParam<Topup>(exc.getMessage(), exc.EXCEPTION_CODE);
		}

		return new OperationResultParam<Topup>("OK", "OK", topup);
	}

	@Override
	public OperationResultParam<ArrayList<Topup>> getHistory(@PathVariable Long customerId,
			@RequestParam(value = "count", required = false, defaultValue="5") Integer count) {
		logger.info("Getting topup history for customer {}, count = {}", customerId, count);
		
		ArrayList<Topup> topups;
		try {
			topups = topupServiceGet.getTopupHishoryForCustomer(customerId, count);
		} catch(ServerGenericException exc) {
			logger.error("{}", exc);
			return new OperationResultParam<ArrayList<Topup>>(exc.getMessage(), exc.EXCEPTION_CODE, null);
		}
		
		return new OperationResultParam<ArrayList<Topup>>("OK", "OK", topups);
	}
	
	public OperationResultParam<Integer> getSuccessfulCount(@PathVariable Long customerId, @PathVariable int limit) {
		logger.info("Getting topup count for customer {}", customerId);
		int count;
		
		try {
			count = topupServiceGet.getSuccessfulCount(customerId, limit);
		} catch(ServerGenericException exc) {
			logger.error("{}", exc);
			return new OperationResultParam<Integer>(exc.getMessage(), exc.EXCEPTION_CODE, null);
		}
		
		return new OperationResultParam<Integer>("OK", "OK", new Integer(count));
	}
}