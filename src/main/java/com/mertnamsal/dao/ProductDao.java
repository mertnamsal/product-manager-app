package com.mertnamsal.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mertnamsal.entity.Customer;
import com.mertnamsal.entity.Product;

public class ProductDao implements ICrud<Product> {

	public void save(Product product) {
		Transaction transaction =null;
		try(Session session = dataBaseConnectionHibernate()){
			transaction = session.beginTransaction();
			session.save(product);
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
	public void update(Product product) {
		Transaction transaction =null;
		try(Session session = dataBaseConnectionHibernate()){
			transaction = session.beginTransaction();
			session.merge(product); //update ile patlıyor
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
	public List<Product> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select p from Product as p order by p.id";
		TypedQuery<Product> typedQuery = session.createQuery(query, Product.class);
		List<Product> productList = typedQuery.getResultList();
		return productList;
	}

	@Override
	public Product find(long id) {
		Transaction transaction =null;
		Product product = null;
		try(Session session = dataBaseConnectionHibernate()){
			transaction = session.beginTransaction();
			product = (Product)session.createQuery("select product from Product as procut where product.id ='"+id+"'").getSingleResult();
			transaction.commit();
			session.close();
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			
		}
		
		return product;
	}

}
