package com.aspiderngi.inventory.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.db.exception.ResourceConfigNotFoundException;
import com.aspiderngi.inventory.db.exception.ResourceInconsistentStateException;
import com.aspiderngi.inventory.db.exception.SimSwapRequestExistsException;
import com.aspiderngi.inventory.db.exception.SimSwapRequestNotFoundException;
import com.aspiderngi.inventory.db.exception.SimSwapStatusNotFoundException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;
import com.aspiderngi.inventory.service.SimSwapService_Confirm;
import com.aspiderngi.inventory.service.SimSwapService_Create;
import com.aspiderngi.inventory.service.entity.Iccid;

@Component
public class SimSwapBean implements SimSwapController {

	@Autowired
	SimSwapService_Create simSwapServiceCreate;

	@Autowired
	SimSwapService_Confirm simSwapServiceConfirm;

	private static final Logger logger = LoggerFactory.getLogger(SimSwapBean.class);

	@SuppressWarnings("static-access")
	@Override
	public OperationResultParam<Long> swapRequest(@PathVariable Long innerSubscriptionId, @RequestBody Iccid iccid) {
		
		logger.trace("SimSwapBean.swapRequest: SubscriptionID = " + innerSubscriptionId + ", ICCID = " + iccid.getIccid());
		
		Long id = -1L;

		try{
			id = simSwapServiceCreate.createRequest(innerSubscriptionId, iccid.getIccid());
		}catch(SubscriptionNotFoundException exc){
			return new OperationResultParam<Long>(exc.EXCEPTION_CODE, exc.getMessage());
		}catch(ResourceConfigNotFoundException exc){
			return new OperationResultParam<Long>(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (SimSwapStatusNotFoundException exc) {
			return new OperationResultParam<Long>(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (ResourceInconsistentStateException exc) {
			return new OperationResultParam<Long>(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (SimSwapRequestExistsException exc) {
			return new OperationResultParam<Long>(exc.EXCEPTION_CODE, exc.getMessage());
		}

		return new OperationResultParam<Long>("Sim Swap Request Created", "OK", id);
	}

	@SuppressWarnings("static-access")
	@Override
	public OperationResultParam<Long> confirmRequest(@PathVariable Long innerSubscriptionId, @RequestBody Iccid iccid) {

		logger.trace("SimSwapBean.confirmRequest: SubscriptionID = " + innerSubscriptionId + ", ICCID = " + iccid.getIccid());
		
		Long id = -1L;
		
		try {
			id = simSwapServiceConfirm.confirmRequest(innerSubscriptionId, iccid.getIccid());
		} catch (SubscriptionNotFoundException exc) {
			return new OperationResultParam<Long>(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (ResourceConfigNotFoundException exc) {
			return new OperationResultParam<Long>(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (ResourceInconsistentStateException exc) {
			return new OperationResultParam<Long>(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (SimSwapRequestNotFoundException exc) {
			return new OperationResultParam<Long>(exc.EXCEPTION_CODE, exc.getMessage());
		} catch (SimSwapStatusNotFoundException exc) {
			return new OperationResultParam<Long>(exc.EXCEPTION_CODE, exc.getMessage());
		}

		return new OperationResultParam<Long>("Sim Swap Request Confirmed", "OK", id);
	}
}