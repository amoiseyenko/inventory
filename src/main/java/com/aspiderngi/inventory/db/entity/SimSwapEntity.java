package com.aspiderngi.inventory.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Audited
@Table(name = "TBL_SIMSWAP")
public class SimSwapEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="REQUEST_DATE")
	@Type(type="date")
	private Date requestDate;

	@Column(name="REQUEST_ICCID")
	private String iccid;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotAudited
	@JoinColumn(name="SUBSCRIPTION_ID", nullable=true, updatable=false)
	private SubscriptionEntity subscription;

	@OneToOne(fetch=FetchType.LAZY)
	@NotAudited
	@JoinColumn(name="RC_ID", nullable=false, updatable=false)
	ResourceConfigurationEntity resourceConfiguration;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SUBSCRIPTION_STATE_ID", nullable=false, updatable=true)
	private SimSwapStateEntity simSwapState;

	public SimSwapEntity() {
		super();
	}

	public SimSwapEntity(Date requestDate, String iccid, SubscriptionEntity subscription, ResourceConfigurationEntity resourceConfiguration, SimSwapStateEntity simSwapState) {
		super();
		
		this.requestDate = requestDate;
		this.iccid = iccid;
		this.subscription = subscription;
		this.resourceConfiguration = resourceConfiguration;
		this.simSwapState = simSwapState;
	}

	public SimSwapEntity(Long id, Date requestDate, String iccid, SubscriptionEntity subscription, ResourceConfigurationEntity resourceConfiguration, SimSwapStateEntity simSwapState) {
		super();
		
		this.id = id;
		this.requestDate = requestDate;
		this.iccid = iccid;
		this.subscription = subscription;
		this.resourceConfiguration = resourceConfiguration;
		this.simSwapState = simSwapState;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public SubscriptionEntity getSubscription() {
		return subscription;
	}

	public void setSubscription(SubscriptionEntity subscription) {
		this.subscription = subscription;
	}

	public ResourceConfigurationEntity getResourceConfiguration() {
		return resourceConfiguration;
	}

	public void setResourceConfiguration(ResourceConfigurationEntity resourceConfiguration) {
		this.resourceConfiguration = resourceConfiguration;
	}

	public SimSwapStateEntity getSimSwapState() {
		return simSwapState;
	}

	public void setSimSwapState(SimSwapStateEntity simSwapState) {
		this.simSwapState = simSwapState;
	}	
}