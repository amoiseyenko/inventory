package com.aspiderngi.inventory.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TBL_CUSTOMER_SETTINGS")
public class CustomerSettingsEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerEntity customer;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="VALUE")
	private String value;

	public CustomerSettingsEntity(){}
	
	public CustomerSettingsEntity(CustomerEntity customer, String name,
			String value) {
		super();
		this.customer = customer;
		this.name = name;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerSettings [id=" + id + ", name=" + name + ", value="
				+ value + "]";
	}
	
	
	
}
