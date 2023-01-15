package com.mertnamsal.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mertnamsal.entity.Admin;
import com.mertnamsal.entity.Category;
import com.mertnamsal.entity.Customer;
import com.mertnamsal.entity.Product;
import com.mertnamsal.entity.ProductEvaluate;




public class HibernateUtils {
	private static final SessionFactory SESSION_FACTORY = sessionFactoryHibernate();

	private static SessionFactory sessionFactoryHibernate() {

		try {
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(Admin.class);
			configuration.addAnnotatedClass(Category.class);
			configuration.addAnnotatedClass(Customer.class);
			configuration.addAnnotatedClass(Product.class);
			configuration.addAnnotatedClass(ProductEvaluate.class);

			SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

			return factory;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}