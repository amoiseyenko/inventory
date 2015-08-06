package com.aspiderngi.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aspiderngi.inventory.db.dao.CustomerDetailsDaoImpl;
import com.aspiderngi.inventory.db.entity.CustomerDetailsEntity;

@Component
public class EmailService {

	@Autowired
	CustomerDetailsDaoImpl customerDetailsDao;
	
	public Boolean isInDb(String email){
		CustomerDetailsEntity customerDetailsEntity = customerDetailsDao.getByEmail(email);
		
		// TODO: fix it!
		return customerDetailsEntity == null ? false: true;
	}
	
}