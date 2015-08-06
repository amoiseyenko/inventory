package com.aspiderngi.inventory.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_IMSI")
public class ResourceIMSIEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name="IMSI", unique = true, nullable = false, length = 15)
	private String IMSI;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RC_ID", nullable=true, updatable=true)
	private ResourceConfigurationEntity subscriptionResourceConfiguration;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RC_STATE_ID", nullable=false, updatable=true)
	private ResourceConfigurationStateEntity resourceState;

	public ResourceIMSIEntity() {
		super();
	}

	public ResourceIMSIEntity(String imsi, ResourceConfigurationEntity subscriptionResourceConfiguration, ResourceConfigurationStateEntity resourceState) {
		super();

		this.IMSI = imsi;
		this.subscriptionResourceConfiguration = subscriptionResourceConfiguration;
		this.resourceState = resourceState;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long id) {
		ID = id;
	}

	public String getIMSI() {
		return IMSI;
	}

	public void setIMSI(String imsi) {
		IMSI = imsi;
	}

	public ResourceConfigurationEntity getSubscriptionResourceConfiguration() {
		return subscriptionResourceConfiguration;
	}

	public void setSubscriptionResourceConfiguration(ResourceConfigurationEntity subscriptionResourceConfiguration) {
		this.subscriptionResourceConfiguration = subscriptionResourceConfiguration;
	}

	public ResourceConfigurationStateEntity getResourceState() {
		return resourceState;
	}

	public void setResourceState(ResourceConfigurationStateEntity resourceState) {
		this.resourceState = resourceState;
	}	
}