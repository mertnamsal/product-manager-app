package com.mertnamsal.dao;

import java.util.List;

import org.hibernate.Session;

import com.mertnamsal.util.HibernateUtils;

public interface ICrud<T> {

	public void save(T t);
	
	public void update(T t);
	
	public void delete(long id);
	
	public List<T> listAll();
	
	public T find(long id);
	
	default Session dataBaseConnectionHibernate() {

		return HibernateUtils.getSessionFactory().openSession();
	}
}
