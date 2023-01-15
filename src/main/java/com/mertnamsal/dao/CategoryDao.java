package com.mertnamsal.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mertnamsal.entity.Category;
import com.mertnamsal.entity.Product;

public class CategoryDao implements ICrud<Category> {

	@Override
	public void save(Category category) {
		Transaction transaction = null;

		try (Session session = dataBaseConnectionHibernate()) {

			transaction = session.beginTransaction();
			session.save(category);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();

			}
			System.out.println("Saved Error");
		}
		
	}

	@Override
	public void update(Category category) {
		Transaction transaction =null;
		try(Session session = dataBaseConnectionHibernate()){
			transaction = session.beginTransaction();
			
			session.update(category);
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
	public List<Category> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select c from Category as c";
		TypedQuery<Category> typedQuery = session.createQuery(query, Category.class);
		List<Category> categoryList = typedQuery.getResultList();
		return categoryList;
	}

	@Override
	public Category find(long id) {
		Transaction transaction =null;
		Category musteri = null;
		try(Session session = dataBaseConnectionHibernate()){
			transaction = session.beginTransaction();
			musteri = (Category)session.createQuery("select c from Category as c where c.id ='"+id+"'").getSingleResult();
			transaction.commit();
			session.close();
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			
		}
		
		return musteri;
	}

}
