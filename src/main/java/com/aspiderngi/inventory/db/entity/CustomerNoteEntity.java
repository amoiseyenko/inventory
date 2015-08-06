package com.aspiderngi.inventory.db.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;


@Entity
@Table(name="TBL_CUSTOMER_NOTE")
public class CustomerNoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false, unique = true)
	private Long id;
	
	@Column(name="NOTE")
	private String note;

	@ManyToOne
	private CategoryEntity category;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerEntity customer;
	
	@ManyToOne
	@JoinColumn(name="AGENT_ID")
	private AgentEntity agent;
	
	@Column(name="DATE")
	@Type(type="timestamp")
	private Date date;

	public CustomerNoteEntity(){}
	
	public CustomerNoteEntity(String note, CategoryEntity category,
			CustomerEntity customer, AgentEntity agent) {
		super();
		this.note = note;
		this.category = category;
		this.customer = customer;
		this.agent = agent;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public AgentEntity getAgent() {
		return agent;
	}

	public void setAgent(AgentEntity agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "CustomerHistoryEntity [note=" + note + ", category=" + category
				+ ", customer=" + customer + ", agent=" + agent + "]";
	}	
}