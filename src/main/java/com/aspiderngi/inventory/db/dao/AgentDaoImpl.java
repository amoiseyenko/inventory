package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.AgentEntity;

@Repository
public class AgentDaoImpl extends GenericDaoHibernateImpl<AgentEntity, Long> {
	
	public AgentEntity getByLogin(String email){
		return (AgentEntity) getCurrentSession().createCriteria(AgentEntity.class)
				.add(Restrictions.eq("login",email))
				.uniqueResult();
	}
 
}