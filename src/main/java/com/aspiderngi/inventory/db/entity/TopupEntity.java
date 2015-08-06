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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="TBL_TOPUP")
@NamedNativeQueries({
	@NamedNativeQuery(name = "getNumberOfSuccessfulIdealTopups",  query = "SELECT * FROM TBL_TOPUP WHERE CUSTOMER_ID = :customerId AND TOPUP_STATE_ID = 2 AND TOPUP_TYPE_ID = 1 LIMIT :limit", resultClass=TopupEntity.class),
	@NamedNativeQuery(name = "getSuccessfulTopupsByCustomerId",  query = "SELECT * FROM TBL_TOPUP WHERE CUSTOMER_ID = :customerId AND TOPUP_STATE_ID = 2 ORDER BY date DESC LIMIT :limit", resultClass=TopupEntity.class),
	@NamedNativeQuery(name = "getTopupByTransactionId", query = "SELECT * FROM TBL_TOPUP WHERE TRANSACTION_ID = :transactionId", resultClass=TopupEntity.class)
	})
public class TopupEntity {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ISSUER_ID")
	private Long issuerId;
	
	@Column(name="PAYMENT_ID")
	private Long paymentId;
	
	@Column(name="PAYMENT_REF")
	private String paymentReference;
	
	@Column(name="PAYMENT_DESRIPTION")
	private String paymentDescription;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerEntity customer;
	
	@Column(name="TOPUP_AMOUNT")
	private Double topUpAmount;
	
	@Column(name="RETURN_URL")
	private String returnUrl;
	
	@Column(name="DATE")
	@Type(type="timestamp")
	private Date date;

	@Column(name="TRANSACTION_ID")
	private String transactionId;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TOPUP_STATE_ID", nullable=false, updatable=true)
	private TopupStateEntity topupState;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TOPUP_TYPE_ID", nullable=false, updatable=true)
	private TopupTypeEntity topupType;
	
	public TopupEntity() {}

	public TopupEntity(Long id, Long issuerId, Long paymentId,
			String paymentReference, String paymentDescription,
			CustomerEntity customer, Double topUpAmount, String returnUrl,
			Date date, String transactionId, 
			TopupStateEntity topupState,
			TopupTypeEntity topupType) {
		super();
		this.id = id;
		this.issuerId = issuerId;
		this.paymentId = paymentId;
		this.paymentReference = paymentReference;
		this.paymentDescription = paymentDescription;
		this.customer = customer;
		this.topUpAmount = topUpAmount;
		this.returnUrl = returnUrl;
		this.date = date;
		this.transactionId = transactionId;
		this.topupState = topupState;
		this.topupType = topupType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentReference() {
		return paymentReference;
	}

	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	public String getPaymentDescription() {
		return paymentDescription;
	}

	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public Double getTopUpAmount() {
		return topUpAmount;
	}

	public void setTopUpAmount(Double topUpAmount) {
		this.topUpAmount = topUpAmount;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public TopupStateEntity getTopupState() {
		return topupState;
	}

	public void setTopupState(TopupStateEntity topupState) {
		this.topupState = topupState;
	}

	public TopupTypeEntity getTopupType() {
		return topupType;
	}

	public void setTopupType(TopupTypeEntity topupType) {
		this.topupType = topupType;
	}

	@Override
	public String toString() {
		return "TopupEntity [id=" + id + ", issuerId=" + issuerId
				+ ", paymentId=" + paymentId + ", paymentReference="
				+ paymentReference + ", paymentDescription="
				+ paymentDescription + ", customer=" + customer
				+ ", topUpAmount=" + topUpAmount + ", returnUrl=" + returnUrl
				+ ", date=" + date + ", transactionId=" + transactionId
				+ ", topupState=" + topupState
				+ ", topupType=" + topupType + "]";
	}
}