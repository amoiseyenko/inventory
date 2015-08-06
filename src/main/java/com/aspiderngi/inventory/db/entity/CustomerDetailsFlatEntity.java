package com.aspiderngi.inventory.db.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(name = "getCustomerArrayForParameters", query = "SELECT * FROM view_customersimresourcesflat WHERE"
			+ " CAST (msisdn as TEXT) LIKE :msisdn "
			+ " AND ('%%' = :first_name OR FIRST_NAME LIKE :first_name )"
			+ " AND EMAIL LIKE :email ",resultClass=CustomerDetailsFlatEntity.class )
})
@Immutable
@Subselect(value="")
public class CustomerDetailsFlatEntity {

	@Id
	@Column(name="ID")
	private Long id;

	@Column(name="DOB")
	private Date dob;

	@Column(name="EMAIL")
	private String email;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="GENDER")
	private String gender;

	@Column(name="RCID")
	private Long rcid;

	@Column(name="SUBSCRIPTION_ID")
	private Long subscriptionId;

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

	public CustomerDetailsFlatEntity(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getRcid() {
		return rcid;
	}

	public void setRcid(Long rcid) {
		this.rcid = rcid;
	}

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Long getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(Long msisdn) {
		this.msisdn = msisdn;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getPin1() {
		return pin1;
	}

	public void setPin1(String pin1) {
		this.pin1 = pin1;
	}

	public String getPin2() {
		return pin2;
	}

	public void setPin2(String pin2) {
		this.pin2 = pin2;
	}

	public String getPuk1() {
		return puk1;
	}

	public void setPuk1(String puk1) {
		this.puk1 = puk1;
	}

	public String getPuk2() {
		return puk2;
	}

	public void setPuk2(String puk2) {
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

	@Override
	public String toString() {
		return "CustomerDetailsFlatEntity [id=" + id + ", dob=" + dob
				+ ", emailString=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", rcid="
				+ rcid + ", subscriptionId=" + subscriptionId + ", msisdn="
				+ msisdn + ", imsi=" + imsi + ", iccid=" + iccid + ", pin1="
				+ pin1 + ", pin2=" + pin2 + ", puk1=" + puk1 + ", puk2=" + puk2
				+ ", iccidState=" + iccidState + ", imsiState=" + imsiState
				+ ", msisdnState=" + msisdnState + "]";
	}
}