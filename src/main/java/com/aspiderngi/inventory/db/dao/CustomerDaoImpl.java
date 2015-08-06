package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.CustomerEntity;

@Repository
public class CustomerDaoImpl extends GenericDaoHibernateImpl<CustomerEntity, Long> {
	
	public CustomerEntity getByEmail(String email ){
		return (CustomerEntity) this.getCurrentSession().createCriteria(CustomerEntity.class)
				.createCriteria("details")
					.add(Restrictions.eq("email", email))
				.uniqueResult();
	}

}