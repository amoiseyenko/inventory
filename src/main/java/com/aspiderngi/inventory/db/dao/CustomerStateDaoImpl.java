package com.aspiderngi.inventory.db.dao;

import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.CustomerStateEntity;

@Repository
public class CustomerStateDaoImpl extends GenericDaoHibernateImpl<CustomerStateEntity, Long> {

}