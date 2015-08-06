package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.SimSwapStateEntity;

@Repository
public class SimSwapStateDaoImpl extends GenericDaoHibernateImpl<SimSwapStateEntity, Long> {
	
	public SimSwapStateEntity getStateByName(String name) {
		return (SimSwapStateEntity) getCurrentSession().createCriteria(SimSwapStateEntity.class).add(Restrictions.naturalId()
				.set("name", name)
			).uniqueResult();
	}
}