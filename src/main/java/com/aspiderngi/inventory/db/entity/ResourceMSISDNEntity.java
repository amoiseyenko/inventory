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
@Table(name = "TBL_MSISDN")
public class ResourceMSISDNEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name="MSISDN", nullable = false)
	private Long MSISDN;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RC_ID", nullable=true, updatable=true)
	private ResourceConfigurationEntity subscriptionResourceConfiguration;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RC_STATE_ID", nullable=false, updatable=true)
	private ResourceConfigurationStateEntity resourceState;

	public ResourceMSISDNEntity() {
		super();
	}

	public ResourceMSISDNEntity(Long msisdn, ResourceConfigurationEntity subscriptionResourceConfiguration, ResourceConfigurationStateEntity resourceState) {
		super();

		this.MSISDN = msisdn;
		this.subscriptionResourceConfiguration = subscriptionResourceConfiguration;
		this.resourceState = resourceState;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long id) {
		ID = id;
	}

	public Long getMSISDN() {
		return MSISDN;
	}

	public void setMSISDN(Long msisdn) {
		MSISDN = msisdn;
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