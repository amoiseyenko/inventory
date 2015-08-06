package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.ResourceConfigurationEntity;

@Repository
public class ResourceConfigurationDaoImpl extends GenericDaoHibernateImpl<ResourceConfigurationEntity, Long> {
	
	public ResourceConfigurationEntity getBySubscriptionId(Long id) {
		return (ResourceConfigurationEntity) getCurrentSession()
				.createCriteria(ResourceConfigurationEntity.class)
				.add(Restrictions.naturalId()
						.set("subscription.id", id)
			).uniqueResult();
	} 

}