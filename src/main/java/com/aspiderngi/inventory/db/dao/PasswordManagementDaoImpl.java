package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.CustomerEntity;
import com.aspiderngi.inventory.db.entity.PasswordMgmtEntity;

@Repository
public class PasswordManagementDaoImpl extends GenericDaoHibernateImpl<PasswordMgmtEntity, Long> {
	
	public PasswordMgmtEntity getForgotPasswordToken(Long customerid, String token) {
		PasswordMgmtEntity e = (PasswordMgmtEntity) this.getCurrentSession()
				.createCriteria(PasswordMgmtEntity.class)
				.add(Restrictions.naturalId())
				.add(Restrictions.eq("token", token))
				.uniqueResult();
		return e;
	}
	
	public PasswordMgmtEntity getForgotPasswordTokenByCustomerId(Long customerid) {
		PasswordMgmtEntity e = (PasswordMgmtEntity) this.getCurrentSession()
				.createCriteria(PasswordMgmtEntity.class)
				.add(Restrictions.naturalId())
				.add(Restrictions.isNull("activationDate"))
				.uniqueResult();
		return e;
	}
}
