package com.aspiderngi.inventory.db.dao;

import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.CustomerAddressEntity;

@Repository
public class CustomerAddressDaoImpl extends GenericDaoHibernateImpl<CustomerAddressEntity, Long> {

}