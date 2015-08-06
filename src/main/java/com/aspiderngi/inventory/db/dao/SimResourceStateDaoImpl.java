package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.ResourceConfigurationStateEntity;

@Repository
public class SimResourceStateDaoImpl extends GenericDaoHibernateImpl<ResourceConfigurationStateEntity, Long> {

	public ResourceConfigurationStateEntity getByName(String name) {
		return (ResourceConfigurationStateEntity) sessionFactory.getCurrentSession().createCriteria(ResourceConfigurationStateEntity.class).add(Restrictions.naturalId()
				.set("name", name)
			).uniqueResult();
	}

}