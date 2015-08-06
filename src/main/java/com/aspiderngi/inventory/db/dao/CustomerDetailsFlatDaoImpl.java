package com.aspiderngi.inventory.db.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.CustomerDetailsFlatEntity;

@Repository
public class CustomerDetailsFlatDaoImpl extends GenericDaoHibernateImpl<CustomerDetailsFlatEntity, Long> {
	
	@SuppressWarnings("unchecked")
	public ArrayList<CustomerDetailsFlatEntity> getCustomerArrayByStrictParameters(String email, String firstName,String msisdn) {
		return (ArrayList<CustomerDetailsFlatEntity>)
				getCurrentSession().getNamedQuery("getCustomerArrayForParameters")
				.setString("msisdn", "%" + msisdn + "%" )
					.setString("email", "%" + email + "%" )
						.setString("first_name", "%" + firstName + "%" )
				.list();
	}
 
	
	

}