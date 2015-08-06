package com.aspiderngi.inventory.db.dao;

import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.ResourceICCIDEntity;

@Repository
public class SimResourceICCIDDaoImpl extends GenericDaoHibernateImpl<ResourceICCIDEntity, Long> {
}