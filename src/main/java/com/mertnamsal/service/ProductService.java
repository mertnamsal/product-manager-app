package com.mertnamsal.service;

import java.util.List;

import com.mertnamsal.dao.ProductDao;
import com.mertnamsal.entity.Product;

public class ProductService {
	
	ProductDao productDao;
	
	

	public ProductService() {
		this.productDao = new ProductDao();
	}



	public void save(Product product) {
		productDao.save(product);
		
	}



	public Product getById(long i) {
		List<Product> list =productDao.listAll().stream().filter((s)->s.getId()==i).toList();
		return list.get(0);
	}



	public List<Product> listAll() {
		return productDao.listAll();
	}



	public List<Product> listLessThan10Stock() {
		return productDao.listAll().stream().filter((s)->s.getStock()<10).toList();
	}



	public void update(Product product) {
		productDao.update(product);
		
	}



	public Product findByName(String productName) {
		List<Product> product =productDao.listAll().stream().filter(s->s.getName().equalsIgnoreCase(productName)).toList();
		System.out.println(product.get(0).getName());
		return product.get(0);
	}




}
