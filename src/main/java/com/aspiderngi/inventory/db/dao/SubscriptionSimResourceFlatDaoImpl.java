package com.aspiderngi.inventory.db.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.SubscriptionSimResourceFlatEntity;

@Repository
public class SubscriptionSimResourceFlatDaoImpl extends GenericDaoHibernateImpl<SubscriptionSimResourceFlatEntity, Long> {

	@SuppressWarnings("unchecked")
	public List<SubscriptionSimResourceFlatEntity> getByICCID(String iccid) {
		return getCurrentSession().getNamedQuery("findSimResourceByICCID").setString("iccid", iccid).list();
	}

	@SuppressWarnings("unchecked")
	public List<SubscriptionSimResourceFlatEntity> getByMSISDN(Long msisdn) {
		return getCurrentSession().getNamedQuery("findSimResourceByMSISDN").setLong("msisdn", msisdn).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<SubscriptionSimResourceFlatEntity> getBySubscriptionId(String subscriptionId) {
		return getCurrentSession().getNamedQuery("findSimResourceBySubscriptionId").setString("subscriptionId", subscriptionId).list();
	}
}