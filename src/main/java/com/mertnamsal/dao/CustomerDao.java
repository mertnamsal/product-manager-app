package com.mertnamsal.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mertnamsal.entity.Customer;
import com.mertnamsal.entity.Product;

public class CustomerDao implements ICrud<Customer> {

	@Override
	public void save(Customer t) {
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
	public void update(Customer customer) {
		Transaction transaction =null;
		try(Session session = dataBaseConnectionHibernate()){
			transaction = session.beginTransaction();
			
			session.merge(customer);
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
	public List<Customer> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select customer from Customer as customer";
		TypedQuery<Customer> typedQuery = session.createQuery(query, Customer.class);
		List<Customer> customerList = typedQuery.getResultList();
		return customerList;
	}

	@Override
	public Customer find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean check(String email, String password) {
		
		Transaction transaction =null;
		List<Customer> customer = null;
		try(Session session = dataBaseConnectionHibernate()){
			transaction = session.beginTransaction();
			customer = session.createQuery("select c from Customer as c where c.email ='"+email+"' and c.password ='"+password+"'").getResultList();
			transaction.commit();
			session.close();
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			
		}
		if(customer == null) {
			return false;
		}else {
			return true;
		}
		
		
	}

}
