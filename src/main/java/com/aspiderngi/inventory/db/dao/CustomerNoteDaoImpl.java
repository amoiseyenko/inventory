package com.aspiderngi.inventory.db.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.CustomerNoteEntity;

@Repository
public class CustomerNoteDaoImpl extends GenericDaoHibernateImpl<CustomerNoteEntity, Long> {
 
	
	@SuppressWarnings("unchecked")
	public List<CustomerNoteEntity> getByCustomerId(Long customerId, Integer position, Integer itemsCount){
		return (List<CustomerNoteEntity>) getCurrentSession().
				createCriteria(CustomerNoteEntity.class)
				.addOrder(Order.desc("date"))
				.add(Restrictions.naturalId().set("customer.id", customerId))
				.setFirstResult(position)
				.setMaxResults(itemsCount)
				.list();
	}
}