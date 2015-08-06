package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.SubscriptionEntity;

@Repository
public class SubscriptionDaoImpl extends GenericDaoHibernateImpl<SubscriptionEntity, Long> {
	
	public SubscriptionEntity getBySubscriptionID(String subscriptionId) {
		return (SubscriptionEntity) getCurrentSession().createCriteria(SubscriptionEntity.class).add(Restrictions.naturalId()
				.set("subscriptionId", subscriptionId)
			).uniqueResult();
	}
	
	public SubscriptionEntity getByInnerSubscriptionID(Long subscriptionId) {
		return (SubscriptionEntity) getCurrentSession().createCriteria(SubscriptionEntity.class).add(Restrictions.naturalId()
				.set("id", subscriptionId)
			).uniqueResult();
	}
}