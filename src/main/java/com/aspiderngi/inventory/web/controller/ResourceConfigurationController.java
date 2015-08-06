package com.aspiderngi.inventory.web.controller;

import java.util.List;

import org.springframework.http.MediaType;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aspiderngi.common.service.entity.ResourceConfiguration;
import com.aspiderngi.common.service.entity.ResourceConfigurationImportResult;
import com.aspiderngi.common.service.entity.SimCardResourceFlat;
import com.aspiderngi.common.service.entity.result.OperationResultParam;

@RestController
@RequestMapping("/rc")
public interface ResourceConfigurationController {

	/*
	 * Example request with CURL:
	 * curl -H "Content-Type: application/json" -X GET http://localhost:8080/inventory-mgmt/rc/getbyiccid/8931162111640100021
	 * */
	@RequestMapping(value = "/getByICCID/{iccid}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<SimCardResourceFlat> getByICCID(@PathVariable String iccid);
	
	/*
	 * Example request with CURL:
	 * curl -H "Content-Type: application/json" -X GET http://localhost:8080/inventory-mgmt/rc/getbymsisdn/639849231
	 * */
	@RequestMapping(value = "/getByMSISDN/{msisdn}" , 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<SimCardResourceFlat> getByMSISDN(@PathVariable Long msisdn);  

	/*
	 * Example request with CURL:
	 * curl -H "Content-Type: application/json" -X POST http://localhost:8080/inventory-mgmt/rc/ \
	 *      -d '[ { "iccid": "8931162111640100001", "pin1": "0001", "pin2": "32013001", "puk1": "2001", "puk2": "13031001", "msisdn": 639849001, "imsi": "204164549145001"}, { "iccid": "8931162111640100002", "pin1": "0002", "pin2": "32013002", "puk1": "2002", "puk2": "13031002", "msisdn": 639849002, "imsi": "204164549145002"}, { "iccid": "8931162111640100003", "pin1": "0003", "pin2": "32013003", "puk1": "2003", "puk2": "13031003", "msisdn": 639849003, "imsi": "204164549145003"} ]' -i
	*/
	@RequestMapping(value = "/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody OperationResultParam<List<ResourceConfigurationImportResult>> importResourceConfiguration(@RequestBody List<ResourceConfiguration> resources);
}