package com.aspiderngi.inventory.db.entity;

import java.util.EnumSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.aspiderngi.common.service.entity.enums.SubscriptionSuspendType;
import com.aspiderngi.inventory.db.utils.SubscriptionSuspendTypeConverter;

@Entity
@Table(name = "TBL_SUBSCRIPTION", uniqueConstraints = @UniqueConstraint(columnNames = {"SUBSCRIPTION_ID", "SUBSCRIPTION_PROVIDER_ID"}))
public class SubscriptionEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="SUBSCRIPTION_ID")
	private String subscriptionId;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SUBSCRIPTION_PROVIDER_ID", nullable=false, updatable=false)
	private SubscriptionProviderEntity subscriptionProvider;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SUBSCRIPTION_STATE_ID", nullable=false, updatable=true)
	private SubscriptionStateEntity subscriptionState;

	@OneToMany(mappedBy="subscription")
	private List<ResourceConfigurationEntity> subscriptionResourceConfiguration;

	@OneToMany(mappedBy="subscription")
	private List<CustomerEntity> customers;

	@SuppressWarnings("rawtypes")
	@Column(name="SUSPEND_STATE", nullable = true, unique = false, columnDefinition="integer")
	@Convert(converter = SubscriptionSuspendTypeConverter.class)
	private EnumSet subscriptionSuspendState;
	
	public SubscriptionEntity() {
		super();
	}

	public SubscriptionEntity(String subscriptionId, SubscriptionProviderEntity subscriptionProvider, SubscriptionStateEntity subscriptionState) {
		super();

		this.subscriptionId = subscriptionId;
		this.subscriptionProvider = subscriptionProvider;
		this.subscriptionState = subscriptionState;
		
		subscriptionSuspendState = EnumSet.noneOf(SubscriptionSuspendType.class);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	}
	
	public String getSubscriptionID() {
		return subscriptionId;
	}

	public void setSubscriptionID(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public SubscriptionProviderEntity getSubscriptionProvider() {
		return subscriptionProvider;
	}

	public void setSubscriptionProvider(SubscriptionProviderEntity subscriptionProvider) {
		this.subscriptionProvider = subscriptionProvider;
	}

	public SubscriptionStateEntity getSubscriptionState() {
		return subscriptionState;
	}

	public void setSubscriptionState(SubscriptionStateEntity subscriptionState) {
		this.subscriptionState = subscriptionState;
	}

	public List<ResourceConfigurationEntity> getSubscriptionResourceConfiguration() {
		return subscriptionResourceConfiguration;
	}

	public void setSubscriptionResourceConfiguration(List<ResourceConfigurationEntity> subscriptionResourceConfiguration) {
		this.subscriptionResourceConfiguration = subscriptionResourceConfiguration;
	}

	public EnumSet getSubscriptionSuspendState() {
		return subscriptionSuspendState;
	}

}