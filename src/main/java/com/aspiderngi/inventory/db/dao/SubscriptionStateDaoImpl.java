package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.SubscriptionStateEntity;

@Repository
public class SubscriptionStateDaoImpl extends GenericDaoHibernateImpl<SubscriptionStateEntity, Long> {

	public SubscriptionStateEntity getByName(String name) {
		return (SubscriptionStateEntity) sessionFactory.getCurrentSession().createCriteria(SubscriptionStateEntity.class).add(Restrictions.naturalId()
				.set("name", name)
			).uniqueResult();
	}
}