package com.aspiderngi.inventory.db.dao.common;

import java.io.Serializable;

/**
 * CRU(D) operations.
 * */
public interface GenericDao <T, PK extends Serializable> {

	/**
	 * Persist the newInstance object into database
	 * */
    PK create(T newInstance);

    /** 
     * Retrieve an object that was previously persisted to the database using the indicated id as primary key
     */
    T read(PK id);

    /** 
     * Save changes made to a persistent object  
     * */
    void update(T transientObject);

}