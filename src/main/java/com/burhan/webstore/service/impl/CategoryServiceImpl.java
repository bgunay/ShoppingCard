package com.burhan.webstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.burhan.webstore.domain.Category;
import com.burhan.webstore.domain.repository.CategoryTree;
import com.burhan.webstore.domain.repository.CatogeryOperations;
import com.burhan.webstore.domain.repository.MyMongoRepository;
import com.burhan.webstore.service.CategoryService;
import com.burhan.webstore.util.Util;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	@Qualifier("categoryRepository")
	private MyMongoRepository<Category> categoryRepository;

	@Autowired
	@Qualifier("categoryRepository")
	private CatogeryOperations catogeryOperations;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.getAllObjects();
	}

	@Autowired
	Util util;
	
	public List<CategoryTree> getCategoryTree() {
		List<Category> categories = categoryRepository.getAllObjects();
		List<CategoryTree> categoryTree = new ArrayList<CategoryTree>();
		categoryTree = util.createTree(categories);
		return categoryTree;
	}


	public List<Category> getAllCategoryNames() {
		List<Category> clist = categoryRepository.getAllObjects();
		return clist;
	}

	@Override
	public Set<Category> getCategoriesByfilter(Map<String, List<String>> filterParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getCategoryById(String categoryId) {
		return categoryRepository.getObject(categoryId);
	}

	@Override
	public void addCategory(Category category) {
		categoryRepository.saveObject(category);
	}

}
