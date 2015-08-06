package com.aspiderngi.inventory.db.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TBL_RESOURCE_CONFIGURATION")
public class ResourceConfigurationEntity {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	// TODO: make one-to-one
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SUBSCRIPTION_ID", nullable=true, updatable=true)
	private SubscriptionEntity subscription;
	
	@OneToMany(mappedBy="subscriptionResourceConfiguration", fetch=FetchType.EAGER)
	private Set<ResourceICCIDEntity> iccids = new HashSet<>();
	
	@OneToMany(mappedBy="subscriptionResourceConfiguration", fetch=FetchType.EAGER)
	private Set<ResourceIMSIEntity> imsis = new HashSet<>();
	
	@OneToMany(mappedBy="subscriptionResourceConfiguration", fetch=FetchType.EAGER)
	private Set<ResourceMSISDNEntity> msisdns = new HashSet<>();

	public ResourceConfigurationEntity() {
		super();
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SubscriptionEntity getSubscription() {
		return subscription;
	}

	public void setSubscription(SubscriptionEntity subscription) {
		this.subscription = subscription;
	}

	public Set<ResourceICCIDEntity> getIccids() {
		return iccids;
	}

	public void setIccids(Set<ResourceICCIDEntity> iccids) {
		this.iccids = iccids;
	}

	public Set<ResourceIMSIEntity> getImsis() {
		return imsis;
	}

	public void setImsis(Set<ResourceIMSIEntity> imsis) {
		this.imsis = imsis;
	}

	public Set<ResourceMSISDNEntity> getMsisdns() {
		return msisdns;
	}

	public void setMsisdns(Set<ResourceMSISDNEntity> msisdns) {
		this.msisdns = msisdns;
	}

}