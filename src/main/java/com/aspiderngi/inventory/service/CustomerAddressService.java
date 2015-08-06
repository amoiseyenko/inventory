package com.aspiderngi.inventory.service;

import java.text.MessageFormat;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aspiderngi.common.service.entity.CustomerAddress;
import com.aspiderngi.inventory.db.dao.CustomerAddressDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerDaoImpl;
import com.aspiderngi.inventory.db.entity.CustomerAddressEntity;
import com.aspiderngi.inventory.db.entity.CustomerEntity;
import com.aspiderngi.inventory.db.exception.CustomerNotFoundException;

@Component
public class CustomerAddressService {

	@Autowired
	CustomerDaoImpl customerDao;
	
	@Autowired
	CustomerAddressDaoImpl customerAddressDao;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerAddressService.class);
		
	@Transactional
	public CustomerAddress getCustomerAddress( Long customerId) throws CustomerNotFoundException{
		

		CustomerEntity customerEntity = customerDao.read(customerId);
		if(customerEntity == null){
			logger.warn("Customer with id={0} not found",customerId);
			throw new CustomerNotFoundException(MessageFormat.format("Customer with id={0} not found",customerId));
		}
		CustomerAddress customerAddress = new CustomerAddress();
		
		customerAddress.setAddress(customerEntity.getAddress().getAddress());
		customerAddress.setCity(customerEntity.getAddress().getCity());
		customerAddress.setZipcode(customerEntity.getAddress().getZipCode());
		customerAddress.setHouseNumber(customerEntity.getAddress().getHouseNumber());
		
		return customerAddress;
	}
	
	@Transactional
	public void updateCustomerAddress (Long customerId,CustomerAddress newAddress) throws CustomerNotFoundException{
		logger.info("Updating customer (id={}) address to {}",customerId,newAddress);
		
		CustomerEntity customerEntity = customerDao.read(customerId);
		if(customerEntity == null){
			logger.warn("Customer with id={0} not found",customerId);
			throw new CustomerNotFoundException(MessageFormat.format("Customer with id={0} not found",customerId));
		}
		
		if(customerEntity.getAddress() == null){
			CustomerAddressEntity customerAddress = new CustomerAddressEntity();
			customerAddressDao.create(customerAddress);
			customerEntity.setAddress(customerAddress);
		}
		
		CustomerAddressEntity customerAddress = customerEntity.getAddress();
		
		if(newAddress.getAddress() != null && !newAddress.getAddress().isEmpty()){
			customerAddress.setAddress(newAddress.getAddress());
		}
		if(newAddress.getCity() != null && !newAddress.getCity().isEmpty()){
			customerAddress.setCity(newAddress.getCity());
		}
		if(newAddress.getZipcode() != null && !newAddress.getZipcode().isEmpty()){
			customerAddress.setZipCode(newAddress.getZipcode());
		}
		if(newAddress.getHouseNumber() != null && !newAddress.getHouseNumber().isEmpty()) {
			customerAddress.setHouseNumber(newAddress.getHouseNumber());
		}
		
		customerAddressDao.update(customerAddress);
		
		
	}
	
}
