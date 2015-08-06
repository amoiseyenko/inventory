package com.aspiderngi.inventory.web.controller;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aspiderngi.common.service.entity.UsageDetails;
import com.aspiderngi.common.service.entity.result.OperationResultParam;

@RestController
@RequestMapping("/usage")
public interface UsageController {

	@RequestMapping(value = "/{subscriptionId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			params = {"count"})
	public @ResponseBody OperationResultParam<ArrayList<UsageDetails>> getUsage(@PathVariable Long subscriptionId,
			@RequestParam(value = "position", required = false, defaultValue="0") Long position,
			@RequestParam(value = "count", required = false, defaultValue="30") Integer count);

}