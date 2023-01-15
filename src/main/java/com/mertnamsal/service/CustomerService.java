package com.mertnamsal.service;

import java.util.List;

import com.mertnamsal.dao.CustomerDao;
import com.mertnamsal.entity.Customer;
import com.mertnamsal.entity.Product;

public class CustomerService {

	CustomerDao customerDao;
	
	public CustomerService() {
		customerDao = new CustomerDao();
	}

	public void save(Customer cust) {
		customerDao.save(cust);
		
	}

	public void update(Customer cust) {
		customerDao.update(cust);
		
	}

	public List<Customer> listAll() {
		return customerDao.listAll();
	}

	public boolean check(String email, String password) {
		return customerDao.check(email,password);
		
	}

	public Customer findByEmail(String email) {
		Customer customer = customerDao.listAll().stream().filter((s)-> s.getEmail().equals(email)).findAny().orElse(null);
		return customer;
	}

	
}
