package com.aspiderngi.inventory.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.aspiderngi.common.service.entity.ResourceConfiguration;
import com.aspiderngi.common.service.entity.ResourceConfigurationImportResult;
import com.aspiderngi.common.service.entity.ResourceConfigurationImportResult.UploadOperationStatus;
import com.aspiderngi.common.service.entity.SimCardResourceFlat;
import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceExistException;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceFetchException;
import com.aspiderngi.inventory.db.exception.SubscriptionResourceNotExistException;
import com.aspiderngi.inventory.service.ResourceConfigurationService_Get;
import com.aspiderngi.inventory.service.ResourceConfigurationService_Import;

@Component
public class ResourceConfigurationBean implements ResourceConfigurationController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceConfigurationBean.class);

	@Autowired
	ResourceConfigurationService_Import resourceConfigurationService;
	
	@Autowired
	ResourceConfigurationService_Get getResourceConfigurationService;
	
	@Override
	public OperationResultParam<SimCardResourceFlat> getByICCID(@PathVariable String iccid) {
		logger.trace("SimProfileServiceBean.getByICCID: getByICCID = " + iccid);

		try {
			SimCardResourceFlat simCardResource = getResourceConfigurationService.getByICCID(iccid);
			return new OperationResultParam<SimCardResourceFlat>("OK", "OK", simCardResource);
		} catch (SubscriptionResourceNotExistException e) {
			return new OperationResultParam<SimCardResourceFlat>(e.EXCEPTION_CODE, e.getMessage(), null);
		} catch (SubscriptionResourceFetchException e) {
			return new OperationResultParam<SimCardResourceFlat>(e.EXCEPTION_CODE, e.getMessage(), null);
		}
	}

	@Override
	public OperationResultParam<SimCardResourceFlat> getByMSISDN(@PathVariable Long msisdn) {
		logger.trace("SimProfileServiceBean.getByMSISDN: getByMSISDN = " + msisdn);
		
		try {
			SimCardResourceFlat simCardResource = getResourceConfigurationService.getByMSISDN(msisdn);
			return new OperationResultParam<SimCardResourceFlat>("OK", "OK", simCardResource);
		} catch (SubscriptionResourceNotExistException e) {
			return new OperationResultParam<SimCardResourceFlat>(e.EXCEPTION_CODE, e.getMessage(), null);
		} catch (SubscriptionResourceFetchException e) {
			return new OperationResultParam<SimCardResourceFlat>(e.EXCEPTION_CODE, e.getMessage(), null);
		}
	}

	@Override
	public OperationResultParam<List<ResourceConfigurationImportResult>> importResourceConfiguration(@RequestBody List<ResourceConfiguration> resources) {
		logger.trace("SimProfileServiceBean.importResourceConfiguration: List<ResourceConfiguration> = " + resources.toString());

		List<ResourceConfigurationImportResult> result = new ArrayList<ResourceConfigurationImportResult>();
		for(ResourceConfiguration simCardResource : resources) {
			try {
				resourceConfigurationService.importResourceConfiguration(simCardResource);

				result.add(new ResourceConfigurationImportResult(UploadOperationStatus.OK, simCardResource));
			} catch (SubscriptionResourceExistException e) {
				result.add(new ResourceConfigurationImportResult(UploadOperationStatus.RESOURCE_DUPLICATE, simCardResource));
			} catch (Exception e) {
				logger.error("Unhandled Error occured during importing resource: {0}. Message: {1}", simCardResource.toString(), e.getMessage());

				result.add(new ResourceConfigurationImportResult(UploadOperationStatus.ERROR, simCardResource));
			}
		}

		return new OperationResultParam<List<ResourceConfigurationImportResult>>("OK", "OK", result);
	}
}