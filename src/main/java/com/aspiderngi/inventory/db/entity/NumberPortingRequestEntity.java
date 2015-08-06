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
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "TBL_NP_REQUEST")
public class NumberPortingRequestEntity {

	@Id
	@Column(name="ID", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SUBSCRIPTION_ID", nullable=false, updatable=false)
	private SubscriptionEntity subscription;

	@Column(name="INPORT_MSISDN", nullable = false)
	private Long inPortMSISDN;

	@Column(name="ICCID", unique = false, nullable = false, length = 22)
	private String ICCID;

	@Column(name="WISH_DATE")
	@Type(type="date")
	private Date wishDate;
	
	@Column(name="ACCOUNT_ID", nullable = false, length = 50)
	private String accountID;

	@Column(name="DONOR_NAME", nullable = false, length = 30)
	private String donorName;

	@Column(name="NOTES", nullable = false)
	private String notes;

	
	public NumberPortingRequestEntity() {
		super();
	}

	public NumberPortingRequestEntity(Long inPortMSISDN, String iccid, Date wishDate, String accountID, String donorName, String notes, SubscriptionEntity subscription) {
		super();
		
		this.inPortMSISDN = inPortMSISDN;
		this.ICCID = iccid;
		this.wishDate = wishDate;
		this.accountID = accountID;
		this.donorName = donorName;
		this.notes = notes;

		this.subscription = subscription;
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

	public Long getInPortMSISDN() {
		return inPortMSISDN;
	}

	public void setInPortMSISDN(Long inPortMSISDN) {
		this.inPortMSISDN = inPortMSISDN;
	}

	public String getICCID() {
		return ICCID;
	}

	public void setICCID(String iccid) {
		this.ICCID = iccid;
	}

	public Date getWishDate() {
		return wishDate;
	}

	public void setWishDate(Date wishDate) {
		this.wishDate = wishDate;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}