package com.aspiderngi.inventory.db.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.TopupEntity;

@Repository
public class TopupDaoImpl extends GenericDaoHibernateImpl<TopupEntity, Long> {
	
	@SuppressWarnings("unchecked")
	public ArrayList<TopupEntity> getSuccessfulTopupsByCustomerId(Long customerId, Integer count) {
		return (ArrayList<TopupEntity>) getCurrentSession().getNamedQuery("getSuccessfulTopupsByCustomerId")
				.setLong("customerId", customerId)
				.setInteger("limit", count)
				.list();		
	} 

	public TopupEntity getTopupByTransactionId(String transactionId) {
		return (TopupEntity) getCurrentSession()
				.getNamedQuery("getTopupByTransactionId")
				.setString("transactionId", transactionId)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<TopupEntity> getNumberOfSuccessfulIdealTopups(Long customerId, int count) {
		return (ArrayList<TopupEntity>) getCurrentSession().getNamedQuery("getNumberOfSuccessfulIdealTopups")
				.setLong("customerId", customerId)
				.setInteger("limit", count)
				.list();		
	} 
}