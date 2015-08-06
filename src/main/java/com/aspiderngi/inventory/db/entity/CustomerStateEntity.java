package com.aspiderngi.inventory.db.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "TBL_CUSTOMER_STATE")
public class CustomerStateEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name="NAME")
	String name;
	
	@Column(name="DESCRIPTION")
	String description;
	
	@OneToMany(mappedBy="state")
	List<CustomerEntity> customers;
	
	public CustomerStateEntity(){};

	public CustomerStateEntity(String name, String description,
			List<CustomerEntity> customers) {
		super();
		this.name = name;
		this.description = description;
		this.customers = customers;
	}

	public List<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	}

	public Long getID() {
		return id;
	}

	public void setID(Long iD) {
		id = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CustomerState [ID=" + id + ", name=" + name + ", description="
				+ description + "]";
	}
	
}