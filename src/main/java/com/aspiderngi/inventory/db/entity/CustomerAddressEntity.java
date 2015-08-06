package com.aspiderngi.inventory.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TBL_CUSTOMER_ADDRESS")
public class CustomerAddressEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerEntity customer;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="ZIP_CODE")
	private String zipCode;
	
	@Column(name="CITY")
	private String City;
	
	@Column(name="HOUSE_NUMBER")
	private String houseNumber;

	public CustomerAddressEntity(){}
	
	
	public CustomerAddressEntity(CustomerEntity customer, String address,
			String zipCode, String city, String houseNumber) {
		super();
		this.customer = customer;
		this.address = address;
		this.zipCode = zipCode;
		City = city;
		this.houseNumber = houseNumber;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}
	
	public String getHouseNumber() {
		return this.houseNumber;
	}
	
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	@Override
	public String toString() {
		return "CustomerAddress [id=" + id + ", customer=" + customer
				+ ", houseNumber=" + houseNumber + ", address=" + address + ", zipCode=" + zipCode + ", City="
				+ City + "]";
	}
	
}
