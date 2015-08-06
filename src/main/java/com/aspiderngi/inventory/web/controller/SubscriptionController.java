package com.aspiderngi.inventory.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aspiderngi.artifacts.model.SuspendRequestModel;
import com.aspiderngi.common.service.entity.Subscription;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.common.service.entity.result.OperationResultParam;

@RestController
@RequestMapping("/subscriptions")
public interface SubscriptionController {
	
	/*
	 * Example request with CURL:
	 * curl -H "Content-Type: application/json" -X POST http://localhost:8080/inventory-mgmt/subscriptions \
	 *      -d '{"msisdn": "639849001", "iccid": "8931162111640100001", "imsi": "204164549145001", "subscriptionId": "345f8693-0644-4dd1-a5e9-6a0165dbda73", "subscriptionProviderName": "simpel"}' -i
	*/
	@RequestMapping(value = "/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<Long> createSubscription(@RequestBody Subscription subscription);

	/*
	 * Example request with CURL:
	 * curl -H "Content-Type: application/json" -X PUT http://localhost:8080/aspiderngi-inventory/subscription/activate/345f8693-0644-4dd1-a5e9-6a0165dbda73/simpel
	 * */
	@RequestMapping(value = "active/{subscriptionId}/{spName}", 
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResult activateSubscription(@PathVariable String subscriptionId, @PathVariable String spName);

	/*
	 * Example request with CURL:
	 * curl -H "Content-Type: application/json" -X GET http://localhost:8080/aspiderngi-inventory/subscription/get/345f8693-0644-4dd1-a5e9-6a0165dbda73/simpel
	 * */
	@RequestMapping(value = "get/{subscriptionId}/{spName}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<Subscription> getSubscription(@PathVariable String subscriptionId, @PathVariable String spName);
	
	/*
	 * Example request with CURL:
	 * curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/aspiderngi-inventory/subscription/345f8693-0644-4dd1-a5e9-6a0165dbda73/simpel
	 * */
	@RequestMapping(value = "{subscriptionId}/{spName}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResult terminateSubscription(@PathVariable String subscriptionId, @PathVariable String spName);

	/*
	 * Example request with CURL:
	 * curl -H "Content-Type: application/json" -X PUT http://localhost:8080/aspiderngi-inventory/subscription/suspend/345f8693-0644-4dd1-a5e9-6a0165dbda73/simpel \
	 *      -d '{"setSuspends":[1,2,4], "unsetSuspends":[8]}' 
	 *      -i
	 * */
	@RequestMapping(value = "suspend/{subscriptionId}/{spName}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResult updateSuspendState(@PathVariable String subscriptionId, @PathVariable String spName, @RequestBody SuspendRequestModel suspendStates);

}