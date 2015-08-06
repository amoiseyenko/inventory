package com.aspiderngi.inventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspiderngi.inventory.db.exception.NumberPortingRequestExistException;
import com.aspiderngi.inventory.db.exception.SubscriptionInconsistentStateException;
import com.aspiderngi.inventory.db.exception.SubscriptionNotFoundException;
import com.aspiderngi.inventory.service.entity.NumberPortingRequest;

public class NumberPortingService_Create {

//	@Autowired
//	private DaoSessionInterface serviceSession;
	
	private static final Logger logger = LoggerFactory.getLogger(NumberPortingService_Create.class);

	public NumberPortingService_Create() {
		logger.debug("NumberPortingService_Create.ctor");
	}

	public Long create(NumberPortingRequest model) throws   SubscriptionNotFoundException,
															SubscriptionInconsistentStateException, 
															NumberPortingRequestExistException {
		logger.trace("NumberPortingService_Create.createSubscription: NumberPortingRequest = " + model.toString());
		return -1L;
//		
//		Long id = -1L;
//		Boolean success = true;
//		Long start = System.currentTimeMillis();
//		
//		try {
//			serviceSession.openCurrentSessionWithTransaction();
//
//			SubscriptionProviderEntity spe = serviceSession.getSubscriptionProviderDao().getByName(model.getSubscriptionProviderName());
//			SubscriptionEntity se = serviceSession.getSubscriptionDao().getBySubscriptionID(model.getSubscriptionId());
//			if(se != null && se.getSubscriptionProvider().getId() != spe.getId())
//				throw new SubscriptionNotFoundException("Subscription ID=" + model.getSubscriptionId() + " not found for provider '" + model.getSubscriptionProviderName() + "'");
//			if(se != null && se.getSubscriptionState().getID() == SubscriptionState.ACTIVE.getId())
//				throw new SubscriptionInconsistentStateException("Subscription ID=" + model.getSubscriptionId() + " is already ACTIVE");
//			
//			NumberPortingRequestEntity entity = new NumberPortingRequestEntity();
//			entity.setInPortMSISDN(Long.parseLong(model.getDonor().getInPortMSISDN()));
//			entity.setSubscription(se);
//			entity.setICCID(model.getDonor().getICCID());
//			entity.setWishDate(model.getWishDate());
//			entity.setAccountID(model.getDonor().getAccountID());
//			//TODO: fix here
//			entity.setDonorName("Some Donor!!");
//			
//			id = serviceSession.getNumberPortingDao().create(entity);
//		} catch (ConstraintViolationException ex) {
//			success = false;
//			
//			throw new NumberPortingRequestExistException("Number Porting already exists in pending state.");
//		} catch (Exception ex) {
//			success = false;
//
//			throw ex;
//		} finally {
//			serviceSession.closeCurrentSessionwithTransaction(success);
//			
//			logger.info("Execution time: " + TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - start));
//		}
//
//		return id;
	}
}