package com.aspiderngi.inventory.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aspiderngi.common.service.entity.result.OperationResultParam;
import com.aspiderngi.inventory.service.entity.Iccid;

@RestController
@RequestMapping("/simswap")
public interface SimSwapController {
 
	/*
	 * Example request with CURL:
	 * curl -H "Content-Type: application/json" -X POST http://localhost:8080/inventory-mgmt/simswap/345f8693-0644-4dd1-a5e9-6a0165dbda73 \
	 *      -d '{ "iccid": "8931162111640100001"}'
	 *      -i
	*/
	@RequestMapping(value="/{innerSubscriptionId}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<Long> swapRequest(@PathVariable Long innerSubscriptionId, @RequestBody Iccid iccid);
	
	/*
	 * Example request with CURL:
	 * curl -H "Content-Type: application/json" -X PUT http://localhost:8080/inventory-mgmt/simswap/confirm/345f8693-0644-4dd1-a5e9-6a0165dbda73 \
	 *      -d '{ "iccid": "8931162111640100001"}'
	 *      -i
	*/
	@RequestMapping(value = "/confirm/{innerSubscriptionId}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<Long> confirmRequest(@PathVariable Long innerSubscriptionId, @RequestBody Iccid iccid);

//	TODO: 
//	@RequestMapping(value = "/port", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody OperationResult portRequest(@PathVariable NumberPortingRequest npRequest); 

}