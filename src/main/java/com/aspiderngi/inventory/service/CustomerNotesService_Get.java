package com.aspiderngi.inventory.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspiderngi.common.service.entity.CustomerNote;
import com.aspiderngi.inventory.db.dao.CustomerNoteDaoImpl;
import com.aspiderngi.inventory.db.entity.CustomerNoteEntity;
import com.aspiderngi.inventory.db.exception.CustomerNotesNotFoundException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;
import com.aspiderngi.inventory.db.utils.PopulationUtils;

@Service
public class CustomerNotesService_Get {

	Logger logger = LoggerFactory.getLogger(CustomerNotesService_Get.class);
	
	@Autowired
	CustomerNoteDaoImpl customerNotesDao;
	
	@Transactional
	public List<CustomerNote> getNotes(Long customerId, Integer position, Integer itemsCount) throws ServerGenericException, CustomerNotesNotFoundException {
		List<CustomerNoteEntity> customerNotes = null;
		logger.info("Getting notes for customer id={}",customerId);
		try{
			customerNotes = customerNotesDao.getByCustomerId(customerId,position,itemsCount);
		}catch(Exception exc){
			logger.error("{}",exc);
			throw new ServerGenericException(exc.getMessage());
		}
		if(customerNotes!= null)
			return PopulationUtils.populateCustomerNotes(customerNotes);
		else{
			logger.error("Customer notes not found");
			throw new CustomerNotesNotFoundException();
		}
	}
	
}
