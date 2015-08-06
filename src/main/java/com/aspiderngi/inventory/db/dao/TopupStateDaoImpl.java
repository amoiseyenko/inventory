
package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.TopupStateEntity;

@Repository
public class TopupStateDaoImpl extends GenericDaoHibernateImpl<TopupStateEntity, Long> {
	
	public TopupStateEntity getStateByName(String name) {
		return (TopupStateEntity) getCurrentSession().createCriteria(TopupStateEntity.class)
				.add(Restrictions.naturalId()
				.set("name", name)
			).uniqueResult();
	}
}