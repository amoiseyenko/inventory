package com.aspiderngi.inventory.db.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.aspiderngi.common.service.entity.Agent;
import com.aspiderngi.common.service.entity.AgentDetails;
import com.aspiderngi.common.service.entity.AgentRegistrationInfo;
import com.aspiderngi.common.service.entity.CustomerDetails;
import com.aspiderngi.common.service.entity.CustomerFullInfo;
import com.aspiderngi.common.service.entity.CustomerNote;
import com.aspiderngi.common.service.entity.Topup;
import com.aspiderngi.common.service.entity.enums.TopupState;
import com.aspiderngi.common.service.entity.enums.TopupType;
import com.aspiderngi.inventory.db.entity.AgentEntity;
import com.aspiderngi.inventory.db.entity.CustomerDetailsEntity;
import com.aspiderngi.inventory.db.entity.CustomerDetailsFlatEntity;
import com.aspiderngi.inventory.db.entity.CustomerEntity;
import com.aspiderngi.inventory.db.entity.CustomerNoteEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;
import com.aspiderngi.inventory.db.entity.TopupEntity;
import com.aspiderngi.inventory.db.exception.CustomerDetailsNotFoundException;

public class PopulationUtils {
 
	
	public static AgentEntity populateAgentEntity(AgentRegistrationInfo agent){
		AgentEntity entity = new AgentEntity();
		entity.setLogin(agent.getLogin());
		entity.setPassword(agent.getPassword());
		return entity;
	}
	
	
	public static Agent populateAgent (AgentEntity entity){
		String password = entity.getPassword();
		String userName = entity.getLogin();
		String firstName =entity.getFirstName();
		String lastName = entity.getLastName();
		Long aid = entity.getId();
		
		ArrayList<String> roles = new ArrayList<String>();
		roles.add(entity.getRole().getName());
		
		return new Agent(aid,userName,password,firstName,lastName,0L,roles);
	}
	
	public static ArrayList<CustomerNote> populateCustomerNotes(List<CustomerNoteEntity> customerNotes){
		ArrayList<CustomerNote> populatedList = new ArrayList<CustomerNote>(customerNotes.size());
		for(CustomerNoteEntity entity : customerNotes){
			populatedList.add(populateCustomerNotes(entity));
		}
		return populatedList;
	}
	
	
	private static CustomerNote populateCustomerNotes(
			CustomerNoteEntity entity) {
		CustomerNote note = new CustomerNote();
		
		note.setAgentId(entity.getAgent().getId());
		note.setCategory(entity.getCategory().getName());
		note.setNote(entity.getNote());
		note.setCustomerId(entity.getCustomer().getId());

		return note;
	}

 
	public static CustomerFullInfo populateCustomerInfo(CustomerDetailsFlatEntity customerFlatEntity) {
		CustomerFullInfo populatedCustomerInfo = new CustomerFullInfo();
		if(customerFlatEntity == null)
			return null;

		populatedCustomerInfo.setFirstName(customerFlatEntity.getFirstName());
		populatedCustomerInfo.setLastName(customerFlatEntity.getLastName());
		if(customerFlatEntity.getDob() != null) 
			populatedCustomerInfo.setDob(customerFlatEntity.getDob().toString());
		if(customerFlatEntity.getMsisdn() != null)
			populatedCustomerInfo.setMsisdn(customerFlatEntity.getMsisdn().toString());
		populatedCustomerInfo.setEmail(customerFlatEntity.getEmail());
		populatedCustomerInfo.setGender(customerFlatEntity.getGender());
		populatedCustomerInfo.setPuk(customerFlatEntity.getPuk1());
		
		return populatedCustomerInfo;
	}

	public static ArrayList<CustomerFullInfo> populateCustomerInfoArray(ArrayList<CustomerDetailsFlatEntity> customerFlatEntity) {
		ArrayList<CustomerFullInfo> customerFullInfos = new ArrayList<CustomerFullInfo>();

		for(CustomerDetailsFlatEntity entity :customerFlatEntity){
			customerFullInfos.add(populateCustomerInfo(entity));
		}

		return customerFullInfos;
	}	

	public static CustomerDetails populateCustomerDetails(CustomerEntity customer) throws CustomerDetailsNotFoundException{
		CustomerDetailsEntity detailsEntity = customer.getDetails();
		SubscriptionEntity subscriptionEntity = customer.getSubscription();
		CustomerDetails customerDetails  = new CustomerDetails();
		if(customer.getDetails() == null) {
			throw new CustomerDetailsNotFoundException(MessageFormat.format("Unable to find details for customer  {0} )", customer));
		}
		
		if(customer.getSubscription() == null) {
			throw new CustomerDetailsNotFoundException(MessageFormat.format("Unable to find subscription for customer  {0} )", customer));
		}

		customerDetails.setCustomerId(customer.getDetails().getId());
		customerDetails.setSubscriptionId(subscriptionEntity.getId());
		customerDetails.setFirstName(detailsEntity.getFirstName());
		customerDetails.setLastName(detailsEntity.getLastName());
		customerDetails.setEmail(detailsEntity.getEmail());
		
		if (detailsEntity.getDob() != null) {
			customerDetails.setDob(detailsEntity.getDob().toString());
		}
		if (detailsEntity.getGender() != null && !detailsEntity.getGender().isEmpty()) {
			customerDetails.setGender(detailsEntity.getGender());
		}
		customerDetails.setPassword(detailsEntity.getPassword());

		return customerDetails;
	}	
	
	public static TopupEntity populateTopupEntity(Topup topup){
		TopupEntity entity = new TopupEntity();
		
		entity.setIssuerId(topup.getIssuerId());
		entity.setPaymentDescription(topup.getPaymentDescription());
		entity.setPaymentReference(topup.getPaymentReference());
		entity.setPaymentId(topup.getPaymentId());
		entity.setReturnUrl(topup.getReturnUrl());
		entity.setTopUpAmount(topup.getAmount());
		entity.setDate(new Date());
		entity.setTransactionId(topup.getTransactionId());
		
		return entity;
	}
	
	public static Topup populateTopup(TopupEntity entity){
		Topup topup = new Topup();

		topup.setAmount(entity.getTopUpAmount());
		topup.setPaymentReference(entity.getPaymentReference());
		topup.setPaymentId(entity.getPaymentId());
		topup.setPaymentDescription(entity.getPaymentDescription());
		topup.setIssuerId(entity.getIssuerId());
		topup.setDate(entity.getDate());
		topup.setTopUpType(TopupType.getValue(entity.getTopupType().getId().intValue()));
		topup.setStatus(TopupState.getValue(entity.getTopupState().getId().intValue()));
		
		return topup;
	}

	public static ArrayList<Topup> populateTopupArray(ArrayList<TopupEntity> entityList) {
		ArrayList<Topup> arrayList = new ArrayList<Topup>(entityList.size());
		
		for(TopupEntity entity : entityList){
			arrayList.add(populateTopup(entity));
		}
		
		return arrayList;
	}	
}