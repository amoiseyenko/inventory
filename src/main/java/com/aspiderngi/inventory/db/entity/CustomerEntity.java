package com.aspiderngi.inventory.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Audited
@Table(name = "TBL_CUSTOMER")
public class CustomerEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotAudited
	@JoinColumn
	private SubscriptionEntity subscription;
	
	@ManyToOne
	@JoinColumn(name="STATE_ID")
	private CustomerStateEntity state;
	
	@NotAudited
	@Column(name = "CONFIRMATION_TOKEN")
	private String confirmationToken;
	
	@NotAudited
	@Column(name = "ROLE_ID")
	private Integer roleId;
	
	@NotAudited
	@OneToMany(mappedBy = "customer")
	private List<CustomerSettingsEntity> settings;
	
	@NotAudited
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ADDRESS_ID")
	private CustomerAddressEntity address;
	
	@NotAudited
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DETAILS_ID")
	private CustomerDetailsEntity details;
	
	@NotAudited
	@OneToMany( mappedBy="customer")  
	private List<CustomerNoteEntity> notes;

	
	
	public CustomerEntity(){};
	
	public CustomerEntity(SubscriptionEntity subscription,
			CustomerStateEntity state, String confirmationToken,
			Integer roleId, List<CustomerSettingsEntity> settings,
			CustomerAddressEntity address, CustomerDetailsEntity details,
			List<CustomerNoteEntity> notes) {
		super();
		this.subscription = subscription;
		this.state = state;
		this.confirmationToken = confirmationToken;
		this.roleId = roleId;
		this.settings = settings;
		this.address = address;
		this.details = details;
		this.notes = notes;
	}

	public Long getId() {
		return id;
	} 

	public SubscriptionEntity getSubscription() {
		return subscription;
	}

	public void setSubscription(SubscriptionEntity subscription) {
		this.subscription = subscription;
	}

	public CustomerStateEntity getState() {
		return state;
	}

	public void setState(CustomerStateEntity state) {
		this.state = state;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<CustomerSettingsEntity> getSettings() {
		return settings;
	}

	public void setSettings(List<CustomerSettingsEntity> settings) {
		this.settings = settings;
	}

	public CustomerAddressEntity getAddress() {
		return address;
	}

	public void setAddress(CustomerAddressEntity address) {
		this.address = address;
	}

	public CustomerDetailsEntity getDetails() {
		return details;
	}

	public void setDetails(CustomerDetailsEntity details) {
		this.details = details;
	}

	public List<CustomerNoteEntity> getNotes() {
		return notes;
	}

	public void setNotes(List<CustomerNoteEntity> notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "CustomerEntity [id=" + id + ", subscription=" + subscription
				+ ", state=" + state + ", confirmationToken="
				+ confirmationToken + ", roleId=" + roleId + ", settings="
				+ settings + ", address=" + address + ", details=" + details
				+ ", notes=" + notes + "]";
	}

	// TODO :   web-payment.
 
}