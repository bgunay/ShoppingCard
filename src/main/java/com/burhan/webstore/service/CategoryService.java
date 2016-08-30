package com.burhan.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.burhan.webstore.domain.Category;
import com.burhan.webstore.domain.repository.CategoryTree;

public interface CategoryService {
	List<Category> getAllCategories();

	Set<Category> getCategoriesByfilter(Map<String, List<String>> filterParams);

	Category getCategoryById(String categoryId);

	void addCategory(Category category);
	
	List<Category> getAllCategoryNames();
	
	List<CategoryTree> getCategoryTree();
}
