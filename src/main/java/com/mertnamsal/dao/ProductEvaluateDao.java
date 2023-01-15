package com.mertnamsal.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mertnamsal.entity.Customer;
import com.mertnamsal.entity.ProductEvaluate;

public class ProductEvaluateDao implements ICrud<ProductEvaluate> {

	@Override
	public void save(ProductEvaluate t) {
		Transaction transaction =null;
		try(Session session = dataBaseConnectionHibernate()){
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
			session.close();
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			System.out.println("Saved Error...");
		}
		
	}

	@Override
	public void update(ProductEvaluate productEvaluate) {
		Transaction transaction =null;
		try(Session session = dataBaseConnectionHibernate()){
			transaction = session.beginTransaction();
			
			session.update(productEvaluate);
			transaction.commit();
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			System.out.println("Update Error...");
		}
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductEvaluate> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select a from ProductEvaluate as a";
		TypedQuery<ProductEvaluate> typedQuery = session.createQuery(query, ProductEvaluate.class);
		List<ProductEvaluate> productEvaluateList = typedQuery.getResultList();
		return productEvaluateList;
	}

	@Override
	public ProductEvaluate find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
