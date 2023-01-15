package com.mertnamsal.service;

import java.util.List;

import com.mertnamsal.dao.ProductEvaluateDao;
import com.mertnamsal.entity.ProductEvaluate;

public class ProductEvaluateService {

	ProductEvaluateDao productEvaluateDao;
	
	public ProductEvaluateService() {
		productEvaluateDao = new ProductEvaluateDao();
	}

	public void save(ProductEvaluate productEvaluate) {
		productEvaluateDao.save(productEvaluate);
		
	}

	public List<ProductEvaluate> listAll() {
		return productEvaluateDao.listAll();
	}

	public List<ProductEvaluate> listMyComments(long id) {
		return productEvaluateDao.listAll().stream().filter((s)->s.getCustomer().getId()==id).toList();
	}

	public List<ProductEvaluate> listProductComments(long id) {
		return productEvaluateDao.listAll().stream().filter((s)->s.getProduct().getId()==id).toList();
	}
}
