package com.mertnamsal.service;

import java.util.List;

import com.mertnamsal.dao.CategoryDao;
import com.mertnamsal.entity.Category;

public class CategoryService {

	CategoryDao categoryDao;
	
	public CategoryService() {
		categoryDao = new CategoryDao();
	}
	
	public void save(Category category) {
		categoryDao.save(category);
		
	}

	public Category findById(long id) {
		
		return categoryDao.find(id);
	}

	public List<Category> listAll() {
		return categoryDao.listAll();
	}

	public Category findByName(String categoryName) {
		return categoryDao.listAll().stream().filter(s-> s.getName().equalsIgnoreCase(categoryName)).findAny().orElse(null);
	}

	
}
