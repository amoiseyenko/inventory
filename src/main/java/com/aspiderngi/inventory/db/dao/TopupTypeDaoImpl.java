package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.TopupTypeEntity;

@Repository
public class TopupTypeDaoImpl extends GenericDaoHibernateImpl<TopupTypeEntity, Long> {
	
	public TopupTypeEntity getTypeByName(String name) {
		return (TopupTypeEntity) getCurrentSession().createCriteria(TopupTypeEntity.class)
				.add(Restrictions.naturalId()
				.set("name", name)
			).uniqueResult();
	}
}