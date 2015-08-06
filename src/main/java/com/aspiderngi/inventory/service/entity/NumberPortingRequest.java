package com.aspiderngi.inventory.service.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "NumberPortingRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class NumberPortingRequest {

	@XmlElement(name = "subscriptionId", required = true)
	private String subscriptionId;

	@XmlElement(name = "subscriptionProviderName", required = true)
	private String subscriptionProviderName;
	
	@XmlElement(name="wishDate")
	private Date wishDate;
	
	@XmlElement(name="subscriptionType")
	private String subscriptionType;
		
	@XmlElement(name="notes")
	private String notes = "";
	
	@XmlElement(name="donor")
	private Donor donor;
	
	public class Donor {
		@XmlElement(name="inPortMSISDN")
		private String inPortMSISDN;

		@XmlElement(name="ICCID")
		private String ICCID;

		@XmlElement(name="subscriptionType")
		private String subscriptionType;
		
		@XmlElement(name="customerType")
		private String customerType;
		
		@XmlElement(name="accountID")
		private String accountID;

		@XmlElement(name="donorSP")
		private String serviceProvider;
		
		@XmlElement(name="donorNO")
		private String networkOperator;

		public String getInPortMSISDN() {
			return inPortMSISDN;
		}

		public void setInPortMSISDN(String inPortMSISDN) {
			this.inPortMSISDN = inPortMSISDN;
		}

		public String getICCID() {
			return ICCID;
		}

		public void setICCID(String iCCID) {
			ICCID = iCCID;
		}

		public String getSubscriptionType() {
			return subscriptionType;
		}

		public void setSubscriptionType(String subscriptionType) {
			this.subscriptionType = subscriptionType;
		}

		public String getCustomerType() {
			return customerType;
		}

		public void setCustomerType(String customerType) {
			this.customerType = customerType;
		}

		public String getAccountID() {
			return accountID;
		}

		public void setAccountID(String accountID) {
			this.accountID = accountID;
		}

		public String getServiceProvider() {
			return serviceProvider;
		}

		public void setServiceProvider(String serviceProvider) {
			this.serviceProvider = serviceProvider;
		}

		public String getNetworkOperator() {
			return networkOperator;
		}

		public void setNetworkOperator(String networkOperator) {
			this.networkOperator = networkOperator;
		}

		@Override
		public String toString() {
			return "Donor [inPortMSISDN=" + inPortMSISDN + ", ICCID=" + ICCID + ", subscriptionType=" + subscriptionType + ", customerType=" + customerType + ", accountID=" + accountID + ", serviceProvider=" + serviceProvider + ", networkOperator=" + networkOperator + "]";
		}		
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getSubscriptionProviderName() {
		return subscriptionProviderName;
	}

	public void setSubscriptionProviderName(String subscriptionProviderName) {
		this.subscriptionProviderName = subscriptionProviderName;
	}

	public Date getWishDate() {
		return wishDate;
	}

	public void setWishDate(Date wishDate) {
		this.wishDate = wishDate;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	@Override
	public String toString() {
		return "NumberPortingRequest [subscriptionId=" + subscriptionId + ", subscriptionProviderName=" + subscriptionProviderName + ", wishDate=" + wishDate + ", subscriptionType=" + subscriptionType + ", notes=" + notes + ", donor= [" + donor.toString() + "] ]";
	}
	
}