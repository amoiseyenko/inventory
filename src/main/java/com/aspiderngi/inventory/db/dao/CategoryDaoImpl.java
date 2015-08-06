package com.aspiderngi.inventory.db.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aspiderngi.inventory.db.dao.common.GenericDaoHibernateImpl;
import com.aspiderngi.inventory.db.entity.CategoryEntity;

@Repository
public class CategoryDaoImpl extends GenericDaoHibernateImpl<CategoryEntity, Long> {

	public CategoryEntity getForName(String categoryName) {
		return (CategoryEntity) getCurrentSession().createCriteria(CategoryEntity.class)
				.add(Restrictions.eq("name", categoryName))
				.uniqueResult();
	}
 
}