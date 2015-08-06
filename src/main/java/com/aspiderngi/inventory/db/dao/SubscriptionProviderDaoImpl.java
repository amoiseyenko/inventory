package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.SubscriptionProviderEntity;

@Repository
public class SubscriptionProviderDaoImpl extends GenericDaoHibernateImpl<SubscriptionProviderEntity, Long> {

	public SubscriptionProviderEntity getByName(String name) {
		return (SubscriptionProviderEntity)	sessionFactory.getCurrentSession().createCriteria(SubscriptionProviderEntity.class).add(Restrictions.naturalId()
				.set("name", name)
			).uniqueResult();
	}
}