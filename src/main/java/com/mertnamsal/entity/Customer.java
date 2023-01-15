package com.mertnamsal.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Customer extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String password;
	
	@Column(unique = true)
	private long tc;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Product> product;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
	private List<ProductEvaluate> productEvaluate;
	
	public Customer() {
		
	}

	public Customer(String firstname, String lastname, String email, String password, long tc) {
		super(firstname, lastname, email);
		this.password = password;
		this.tc = tc;
	}
	

	public Customer(String firstname, String lastname, String email, String password, long tc, List<Product> product) {
		super(firstname, lastname, email);
		this.password = password;
		this.tc = tc;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getTc() {
		return tc;
	}

	public void setTc(long tc) {
		this.tc = tc;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public List<ProductEvaluate> getProductEvaluate() {
		return productEvaluate;
	}

	public void setProductEvaluate(List<ProductEvaluate> productEvaluate) {
		this.productEvaluate = productEvaluate;
	}
	
	
	
	
	
	
	
}
