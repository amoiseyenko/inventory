package com.aspiderngi.inventory.service;

import java.text.MessageFormat;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aspiderngi.common.service.entity.CustomerNote;
import com.aspiderngi.inventory.db.dao.AgentDaoImpl;
import com.aspiderngi.inventory.db.dao.CategoryDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerDaoImpl;
import com.aspiderngi.inventory.db.dao.CustomerNoteDaoImpl;
import com.aspiderngi.inventory.db.entity.AgentEntity;
import com.aspiderngi.inventory.db.entity.CategoryEntity;
import com.aspiderngi.inventory.db.entity.CustomerEntity;
import com.aspiderngi.inventory.db.entity.CustomerNoteEntity;
import com.aspiderngi.inventory.db.exception.AgentNotFoundException;
import com.aspiderngi.inventory.db.exception.CategoryNotFoundException;
import com.aspiderngi.inventory.db.exception.CustomerNotFoundException;
import com.aspiderngi.inventory.db.exception.ServerGenericException;

@Component
public class CustomerNotesService_Post {

	Logger logger = LoggerFactory.getLogger(CustomerNotesService_Post.class);
	
	@Autowired
	CustomerNoteDaoImpl noteDao;
	
	@Autowired
	AgentDaoImpl agentDao;
	
	@Autowired
	CustomerDaoImpl customerDao;
	
	@Autowired
	CategoryDaoImpl categoryDao;
	
	@Transactional
	public Long addNote(CustomerNote note) throws CustomerNotFoundException,AgentNotFoundException,CategoryNotFoundException,ServerGenericException{
		logger.info("Adding notes to customer ", note);
		Long noteId = -1L;
		try{
			AgentEntity agentEntity = agentDao.read(note.getAgentId());
			if(agentEntity == null){
				logger.info("Unable to find agent with id{}",note.getAgentId());
				throw new AgentNotFoundException(MessageFormat.format("Unable to find agent with id={0}",note.getAgentId()));
			}
			
			CategoryEntity categoryEntity = categoryDao.getForName(note.getCategory());
			if(categoryEntity == null){
				logger.info("Unable to find category with id{}",note.getCategory());
				throw new CategoryNotFoundException(MessageFormat.format("Unable to find category: {0}",note.getCategory()));
			}

			CustomerEntity customerEntity = customerDao.read(note.getCustomerId());
			if(customerEntity == null){
				logger.info("Unable to find customer with id{}",note.getCustomerId());
				throw new CustomerNotFoundException(MessageFormat.format("Unable to find customer with id={0}", note.getCustomerId()));
			}
			
			CustomerNoteEntity noteEntity = new CustomerNoteEntity();
			
			noteEntity.setAgent(agentEntity);
			noteEntity.setCustomer(customerEntity);
			noteEntity.setCategory(categoryEntity);
			noteEntity.setNote(note.getNote());
			
			noteId = noteDao.create(noteEntity);
		}catch(Exception exc){
			logger.error("{}",exc);
			throw new ServerGenericException();
		} 
		return noteId;
	}
	
}
