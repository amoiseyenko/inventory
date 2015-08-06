package com.aspiderngi.inventory.db.dao.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDaoHibernateImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	@Autowired
	public SessionFactory sessionFactory;
	
	private Class<T> type;
	
	@SuppressWarnings("unchecked")
	public GenericDaoHibernateImpl() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public PK create(T o) {
		return (PK) getCurrentSession().save(o);
	}

	@SuppressWarnings("unchecked")
	public T read(PK id) {
		return (T) getCurrentSession().get(type, id);
	}

	public void update(T o) {
		getCurrentSession().update(o);
	}

}