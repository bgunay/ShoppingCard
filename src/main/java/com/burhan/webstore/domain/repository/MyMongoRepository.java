package com.burhan.webstore.domain.repository;

import java.util.List;

import com.burhan.webstore.domain.Category;
import com.mongodb.WriteResult;

public interface MyMongoRepository<T> {

	public List<T> getAllObjects();

	public void saveObject(T object);

	public T getObject(String id);

	public WriteResult updateObject(String id, T t);

	public void deleteObject(String id);

	public void createCollection();

	public void dropCollection();
	
	default List<T> getObjectByCategory(String t){
		return null;
	}

}