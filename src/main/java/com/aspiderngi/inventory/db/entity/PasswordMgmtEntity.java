package com.aspiderngi.inventory.db.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Type;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Table(name="TBL_PASSWORD_MGMT")
public class PasswordMgmtEntity {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="TOKEN")
	private String token;
	
	@Column(name="REQUESTED_DATE")
	@Type(type="timestamp")
	private Date requestedDate;
	
	@Column(name="ACTIVATION_DATE")
	@Type(type="timestamp")
	private Date activationDate;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerEntity customer;
	
	public PasswordMgmtEntity() {}
	
	public PasswordMgmtEntity(Long id, String token, Date requestedDate, Date activationDate, CustomerEntity customer) {
		super();
		this.id = id;
		this.token = token;
		this.requestedDate = requestedDate;
		this.activationDate = activationDate;
		this.customer = customer;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public Date getRequestedDate() {
		return this.requestedDate;
	}
	
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	
	public Date getActivationDate() {
		return this.activationDate;
	}
	
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
	
	public CustomerEntity getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
}