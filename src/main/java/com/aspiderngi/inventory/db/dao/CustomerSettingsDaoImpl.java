package com.aspiderngi.inventory.db.dao;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.CustomerSettingsEntity;

@Repository
public class CustomerSettingsDaoImpl extends GenericDaoHibernateImpl<CustomerSettingsEntity, Long> {
		
	public List<CustomerSettingsEntity> getSettingsByCustomerId(Long customerId) {
		return (List<CustomerSettingsEntity>) this.getCurrentSession()
				.createCriteria(CustomerSettingsEntity.class)
				.add(Restrictions.naturalId()).list();
	}
}