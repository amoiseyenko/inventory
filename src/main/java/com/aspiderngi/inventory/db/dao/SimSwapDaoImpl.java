package com.aspiderngi.inventory.db.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.SimSwapEntity;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;

@Repository
public class SimSwapDaoImpl extends GenericDaoHibernateImpl<SimSwapEntity, Long> {
	
	@SuppressWarnings("unchecked")
	public List<SimSwapEntity> getListForSubscriptionId(Long subscriptionId){
		return (List<SimSwapEntity>)  getCurrentSession().createCriteria(SimSwapEntity.class)
				.add(Restrictions.naturalId()
						.set("subscription.id", subscriptionId))
				.list();
	}	
	
	@SuppressWarnings("unchecked")
	public List<SimSwapEntity> getListForIccId(String iccId){
		return (List<SimSwapEntity>)  getCurrentSession().createCriteria(SimSwapEntity.class)
				.add(Restrictions.naturalId()
				.set("iccid", iccId)).list();
	}
	
	public SimSwapEntity getBySubscriptionIdAndIccId(SubscriptionEntity subscription,String iccId){
		return (SimSwapEntity)  getCurrentSession().createCriteria(SimSwapEntity.class).
				add(Restrictions.naturalId()
				.set("iccid", iccId)
				.set("subscription", subscription))
				.uniqueResult();
	}
}