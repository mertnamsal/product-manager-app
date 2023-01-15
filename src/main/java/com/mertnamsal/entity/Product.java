package com.mertnamsal.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private int stock;
	
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
	private List<ProductEvaluate> productEvaluate;
	
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "product")
	private List<Customer> customer;

	
	public Product() {
		super();
	}

	

	public Product(String name, int stock, double price, List<Customer> customer) {
		super();
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.customer = customer;
	}



	public Product(String name, int stock, double price,Category category) {
		super();
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.category = category;
		
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomers(List<Customer> customer) {
		this.customer = customer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public List<ProductEvaluate> getProductEvaluate() {
		return productEvaluate;
	}



	public void setProductEvaluate(List<ProductEvaluate> productEvaluate) {
		this.productEvaluate = productEvaluate;
	}



	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
	
	
	
	
	
}
