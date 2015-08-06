package com.aspiderngi.inventory.service;


import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aspiderngi.artifacts.model.CustomerPatchOperation;
import com.aspiderngi.common.service.entity.result.OperationResult;
import com.aspiderngi.inventory.db.dao.CustomerDetailsDaoImpl;
import com.aspiderngi.inventory.db.exception.CustomerPatchIllegalArguments;
import com.aspiderngi.inventory.db.exception.ServerGenericException;

@Component
public class CustomerPatchService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerPatchService.class);

	@Autowired
	CustomerDetailsDaoImpl customerDetailsDao;

	private static Map<String, String> columnNames = new HashMap<String,String>();
	static{
		columnNames.put("FirstName", "first_name");
		columnNames.put("LastName", "last_name");
	}

	@Transactional
	public OperationResult execute(CustomerPatchOperation patchOperation) throws ServerGenericException, CustomerPatchIllegalArguments{
		try{
			patchOperation = translateToColumnNames(patchOperation);
			customerDetailsDao.patch(patchOperation);
		}catch(ParseException exc){
			logger.warn("{}",exc);
			throw new CustomerPatchIllegalArguments(exc.getMessage());
		}
		catch( SQLGrammarException exc){
			logger.warn("{}",exc);
			String message = exc.getSQLException().getMessage().replace(" \"", " [")
					.replace("\" ", "] ").replace("\\n", "");
			throw new CustomerPatchIllegalArguments(message);
		}
		catch(ConstraintViolationException exc) {
			logger.error("{}", exc);
			throw new CustomerPatchIllegalArguments("Constraint violation: " + exc.getConstraintName().toUpperCase());
		}
		catch(Exception exc){
			logger.warn("{}",exc);
			throw new ServerGenericException(exc.getMessage());
		}
		return new OperationResult("OK","OK");
	}


	private CustomerPatchOperation translateToColumnNames(CustomerPatchOperation patchOp){
		String pathString = patchOp.getPath();
		if(columnNames.containsKey(pathString) ){
			String translatedPathName = columnNames.get(pathString);
			patchOp.setPath(translatedPathName);
		}
		return patchOp;
	}
}