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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TBL_ICCID", uniqueConstraints= @UniqueConstraint(columnNames = {"ICCID"}))
public class ResourceICCIDEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name="ICCID", unique = true, nullable = false, length = 22)
	private String ICCID;
	
	@Column(name="PIN1")
	private String PIN1;
	
	@Column(name="PIN2")
	private String PIN2;
	
	@Column(name="PUK1")
	private String PUK1;
	
	@Column(name="PUK2")
	private String PUK2;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RC_ID", nullable=true, updatable=true)
	private ResourceConfigurationEntity subscriptionResourceConfiguration;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RC_STATE_ID", nullable=false, updatable=true)
	private ResourceConfigurationStateEntity resourceState;
	
	public ResourceICCIDEntity() {
		super();
	}

	public ResourceICCIDEntity(String iCCID, String pin1, String pin2, String puk1, String puk2, ResourceConfigurationEntity subscriptionResourceConfiguration, ResourceConfigurationStateEntity resourceState) {
		super();
		
		this.ICCID = iCCID;
		this.PIN1 = pin1;
		this.PIN2 = pin2;
		this.PUK1 = puk1;
		this.PUK2 = puk2;
		this.subscriptionResourceConfiguration = subscriptionResourceConfiguration;
		this.resourceState = resourceState;
	}
	
	public ResourceICCIDEntity(Long id, String iccid, String pin1, String pin2, String puk1, String puk2, ResourceConfigurationEntity subscriptionResourceConfiguration, ResourceConfigurationStateEntity resourceState) {
		super();
		
		this.ID = id;
		this.ICCID = iccid;
		this.PIN1 = pin1;
		this.PIN2 = pin2;
		this.PUK1 = puk1;
		this.PUK2 = puk2;
		this.subscriptionResourceConfiguration = subscriptionResourceConfiguration;
		this.resourceState = resourceState;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long id) {
		ID = id;
	}

	public String getICCID() {
		return ICCID;
	}

	public void setICCID(String iccid) {
		ICCID = iccid;
	}

	public String getPIN1() {
		return PIN1;
	}

	public void setPIN1(String pin1) {
		PIN1 = pin1;
	}

	public String getPIN2() {
		return PIN2;
	}

	public void setPIN2(String pin2) {
		PIN2 = pin2;
	}

	public String getPUK1() {
		return PUK1;
	}

	public void setPUK1(String puk1) {
		PUK1 = puk1;
	}

	public String getPUK2() {
		return PUK2;
	}

	public void setPUK2(String puk2) {
		PUK2 = puk2;
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