package com.aspiderngi.inventory.db.dao;

import java.text.ParseException;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.artifacts.model.CustomerPatchOperation;
import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.CustomerDetailsEntity;

@Repository
public class CustomerDetailsDaoImpl extends GenericDaoHibernateImpl<CustomerDetailsEntity, Long> {
	
	public CustomerDetailsEntity getByEmail(String email){
		return (CustomerDetailsEntity) this.getCurrentSession().createCriteria(CustomerDetailsEntity.class)
				.add(Restrictions.naturalId().set("email", email))
				.uniqueResult();
	}
	
	public int patch(CustomerPatchOperation operation) throws ParseException{
		 SQLQuery query = getCurrentSession().createSQLQuery("UPDATE tbl_customer_details SET "
				+ operation.getPath() + 
				" = :value WHERE id = :id");
				if(operation.isDate()){
					query.setDate("value", operation.getDate());
				}
				else
					query.setString("value", operation.getValue());
				query.setLong("id", operation.getCustomerId());
		return query.executeUpdate();
	}

}