package com.aspiderngi.inventory.db.dao;

import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.NumberPortingRequestEntity;

@Repository
public class NumberPortingDaoImpl extends GenericDaoHibernateImpl<NumberPortingRequestEntity, Long> {

}