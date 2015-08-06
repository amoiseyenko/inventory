package com.aspiderngi.inventory.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(name = "findSimResourceByICCID", query = "SELECT * FROM view_simresourcesflat WHERE ICCID = :iccid ORDER BY ID", resultClass = SubscriptionSimResourceFlatEntity.class),
	@NamedNativeQuery(name = "findSimResourceByMSISDN", query = "SELECT * FROM view_simresourcesflat WHERE MSISDN = :msisdn ORDER BY ID", resultClass = SubscriptionSimResourceFlatEntity.class),
	@NamedNativeQuery(name = "findSimResourceBySubscriptionId", query = "SELECT * FROM view_simresourcesflat WHERE SUBSCRIPTION_ID = :subscriptionId ORDER BY ID", resultClass = SubscriptionSimResourceFlatEntity.class)
})
@Immutable
@Subselect(value="")
public class SubscriptionSimResourceFlatEntity {

	@Id
	@Column(name="ID")
	private Long id;

	@Column(name="SUBSCRIPTION_ID")
	private Long subscriptionId;

	@Column(name="PROVIDER_SUB_ID")
	private String providerSubId;

	@Column(name="PROVIDER_ID")
	private Long providerId;

	@Column(name="MSISDN")
	private Long msisdn;

	@Column(name="IMSI")
	private String imsi;

	@Column(name="ICCID")
	private String iccid;
	
	@Column(name="PIN1")
	private String pin1;
	
	@Column(name="PIN2")
	private String pin2;
	
	@Column(name="PUK1")
	private String puk1;
	
	@Column(name="PUK2")
	private String puk2;
	
	@Column(name="ICCID_STATE")
	private Integer iccidState;

	@Column(name="IMSI_STATE")
	private Integer imsiState;

	@Column(name="MSISDN_STATE")
	private Integer msisdnState;

	public SubscriptionSimResourceFlatEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProviderSubId() {
		return providerSubId;
	}

	public void setProviderSubId(String providerSubId) {
		this.providerSubId = providerSubId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Long getMSISDN() {
		return msisdn;
	}

	public void setMSISDN(Long msisdn) {
		this.msisdn = msisdn;
	}

	public String getIMSI() {
		return imsi;
	}

	public void setIMSI(String imsi) {
		this.imsi = imsi;
	}

	public String getICCID() {
		return iccid;
	}

	public void setICCID(String iccid) {
		this.iccid = iccid;
	}

	public String getPIN1() {
		return pin1;
	}

	public void setPIN1(String pin1) {
		this.pin1 = pin1;
	}

	public String getPIN2() {
		return pin2;
	}

	public void setPIN2(String pin2) {
		this.pin2 = pin2;
	}

	public String getPUK1() {
		return puk1;
	}

	public void setPUK1(String puk1) {
		this.puk1 = puk1;
	}

	public String getPUK2() {
		return puk2;
	}

	public void setPUK2(String puk2) {
		this.puk2 = puk2;
	}

	public Integer getIccidState() {
		return iccidState;
	}

	public void setIccidState(Integer iccidState) {
		this.iccidState = iccidState;
	}

	public Integer getImsiState() {
		return imsiState;
	}

	public void setImsiState(Integer imsiState) {
		this.imsiState = imsiState;
	}

	public Integer getMsisdnState() {
		return msisdnState;
	}

	public void setMsisdnState(Integer msisdnState) {
		this.msisdnState = msisdnState;
	}
}