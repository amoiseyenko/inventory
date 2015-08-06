package com.aspiderngi.inventory.service.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Iccid")
@XmlAccessorType(XmlAccessType.FIELD)
public class Iccid {
	
	@XmlElement(name = "iccid", required = true)
	private String iccid;

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	
	public Iccid( ) {
		super();
	}
	
	public Iccid(String iccid) {
		super();
		this.iccid = iccid;
	}

	@Override
	public String toString() {
		return "Iccid [iccid=" + iccid + "]";
	}
}