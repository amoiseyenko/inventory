package com.aspiderngi.inventory.service.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "subscription_id", "confirmation_token", "state_id", "role_id"})
public class Customer {
 
	@XmlElement(name = "subscription_id", required = true)
	private String subscription_id;
	
	@XmlElement(name = "confirmation_token", required = true)
	private String confirmation_token;

	@XmlElement(name = "state_id", required = true)
	private String state_id;
	
	@XmlElement(name = "role_id", required = true)
	private String role_id;
 
	public Customer(){}
	
	public Customer(Long id, String subscription_id, String confirmation_token,
			String state_id, String role_id) {
		super();
		this.subscription_id = subscription_id;
		this.confirmation_token = confirmation_token;
		this.state_id = state_id;
		this.role_id = role_id;
	}

 	public String getMsisdn() {
		return subscription_id;
	}

	public void setMsisdn(String msisdn) {
		this.subscription_id = msisdn;
	}

	public String getICCID() {
		return confirmation_token;
	}

	public void setICCID(String iccid) {
		this.confirmation_token = iccid;
	}

	public String getImsi() {
		return state_id;
	}

	public void setImsi(String imsi) {
		this.state_id = imsi;
	}

	public String getSubscriptionId() {
		return role_id;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.role_id = subscriptionId;
	}

	@Override
	public String toString() {
		return "Customer [subscription_id=" + subscription_id
				+ ", confirmation_token=" + confirmation_token + ", state_id="
				+ state_id + ", role_id=" + role_id + "]";
	}

	 
}