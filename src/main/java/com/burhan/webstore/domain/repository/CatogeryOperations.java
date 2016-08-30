package com.burhan.webstore.domain.repository;

import java.util.List;

import com.burhan.webstore.domain.Category;

public interface CatogeryOperations {

	public List<Category> getObjectChilds(String parentId);
	public  List<Category> getObjectsByKey(String key, String value);
	public List<Category> getObjectParents(String[] categoryIds);
}
