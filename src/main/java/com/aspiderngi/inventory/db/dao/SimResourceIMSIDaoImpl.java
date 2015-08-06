package com.aspiderngi.inventory.db.dao;

import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.ResourceIMSIEntity;

@Repository
public class SimResourceIMSIDaoImpl extends GenericDaoHibernateImpl<ResourceIMSIEntity, Long> {
}