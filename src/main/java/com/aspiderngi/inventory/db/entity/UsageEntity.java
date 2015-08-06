package com.aspiderngi.inventory.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@NamedStoredProcedureQuery(
		name = "getUsage", 
		procedureName = "dwh.get_usage", parameters = {
			@StoredProcedureParameter(name = "subscription_id", type = Long.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "pointer_id", type = Long.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "count", type = Integer.class, mode = ParameterMode.IN)
		},
		resultClasses = UsageEntity.class)
@Immutable
@Subselect(value="")
public class UsageEntity {

	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="DATE")
	private Date usageDate;
	
	@Column(name="USAGE_TYPE")
	private Integer usageType;

	@Column(name="USAGE_VALUE")
	private Long usageValue;

	@Column(name="USAGE_COST")
	private Long usageCost;
	
	@Column(name="EXTRA")
	private String extra;

	@Column(name="POSITION")
	private Long position;

	public UsageEntity() {}

	public UsageEntity(Long id, Date usageDate, Integer usageType,
			Long usageValue, Long usageCost, String extra, Long position) {
		super();
		this.id = id;
		this.usageDate = usageDate;
		this.usageType = usageType;
		this.usageValue = usageValue;
		this.usageCost = usageCost;
		this.extra = extra;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUsageDate() {
		return usageDate;
	}

	public void setUsageDate(Date usageDate) {
		this.usageDate = usageDate;
	}

	public Integer getUsageType() {
		return usageType;
	}

	public void setUsageType(Integer usageType) {
		this.usageType = usageType;
	}

	public Long getUsageValue() {
		return usageValue;
	}

	public void setUsageValue(Long usageValue) {
		this.usageValue = usageValue;
	}

	public Long getUsageCost() {
		return usageCost;
	}

	public void setUsageCost(Long usageCost) {
		this.usageCost = usageCost;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "UsageEntity [id=" + id + ", usageDate=" + usageDate
				+ ", usageType=" + usageType + ", usageValue=" + usageValue
				+ ", usageCost=" + usageCost + ", extra=" + extra
				+ ", position=" + position + "]";
	}

}