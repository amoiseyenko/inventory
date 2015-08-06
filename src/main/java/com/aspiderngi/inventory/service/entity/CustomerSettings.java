package com.aspiderngi.inventory.service.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CustomerSettings")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"customer_id", "setting_name", "setting_value"})
public class CustomerSettings {
		
	@XmlElement(name = "customer_id", required = true)
	private Long id;
	
	@XmlElement(name = "setting_name", required = true)
	private String setting_name;
	
	@XmlElement(name = "setting_value", required = true)
	private String setting_value;

	public CustomerSettings(){}
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsisdn() {
		return setting_name;
	}

	public void setMsisdn(String msisdn) {
		this.setting_name = msisdn;
	}

	public String getICCID() {
		return setting_value;
	}

	public void setICCID(String iccid) {
		this.setting_value = iccid;
	}

	public String getSetting_name() {
		return setting_name;
	}

	public void setSetting_name(String setting_name) {
		this.setting_name = setting_name;
	}

	public String getSetting_value() {
		return setting_value;
	}

	public void setSetting_value(String setting_value) {
		this.setting_value = setting_value;
	}

	public CustomerSettings(Long id, String setting_name, String setting_value) {
		super();
		this.id = id;
		this.setting_name = setting_name;
		this.setting_value = setting_value;
	}

	@Override
	public String toString() {
		return "CustomerSettings [id=" + id + ", setting_name=" + setting_name
				+ ", setting_value=" + setting_value + "]";
	}

	 
}