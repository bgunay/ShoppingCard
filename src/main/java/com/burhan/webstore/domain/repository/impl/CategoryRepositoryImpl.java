package com.burhan.webstore.domain.repository.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.burhan.webstore.domain.Category;
import com.burhan.webstore.domain.repository.CatogeryOperations;
import com.burhan.webstore.domain.repository.MyMongoRepository;
import com.mongodb.WriteResult;

@Repository(value = "categoryRepository")
public class CategoryRepositoryImpl implements MyMongoRepository<Category>, CatogeryOperations {

	@Autowired
	MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * Get all Categorys.
	 */
	public List<Category> getAllObjects() {
		//dropCollection();
		//createCollection();
		//Category category = new Category("1", "electronics", "");
		//mongoTemplate.insert(category);
		//category = new Category("2", "phones", "1");
		//mongoTemplate.insert(category);
		//category = new Category("3", "tablets", "1");
		//mongoTemplate.insert(category);
		//category = new Category("4", "notebooks", "1");
		//mongoTemplate.insert(category);
		//category = new Category("5", "tvs", "1");
		//mongoTemplate.insert(category);
		//category = new Category("6", "lcdTv", "5");
		//mongoTemplate.insert(category);

		//mongoTemplate.createCollection(CategoryProduct.class);
		//CategoryProduct cp = new CategoryProduct("1", "2", "1");
		//mongoTemplate.insert(cp);
		//CategoryProduct cp2 = new CategoryProduct("2", "4", "1");
		//mongoTemplate.insert(cp2);
		//CategoryProduct cp3 = new CategoryProduct("3", "2", "44");
		//mongoTemplate.insert(cp3);
		//CategoryProduct cp4 = new CategoryProduct("4", "5", "44");
		//mongoTemplate.insert(cp4);

		return mongoTemplate.findAll(Category.class);
	}

	/**
	 * Saves a {@link Category}.
	 * 
	 * @throws IOException
	 */
	public void saveObject(Category category) {
		mongoTemplate.insert(category);
		// gridFsTemplate.store(Category.getCategoryImage().getInputStream(),
		// metadata)
	}

	/**
	 * Gets a {@link Category} for a particular id.
	 */
	public Category getObject(String CategoryId) {
		return mongoTemplate.findOne(new Query(Criteria.where("CategoryId").is(CategoryId)), Category.class);
	}

	public List<Category> getObjectsByKey(String key, String value) {
		return mongoTemplate.find(new Query(Criteria.where(key).is(value)), Category.class);
	}

	public List<Category> getObjectChilds(String categoryId) {
		return mongoTemplate.find(new Query(Criteria.where("parentCategory").is(categoryId)), Category.class);
	}

	public List<Category> getObjectParents(String[] categoryIds) {
		List<Category> parentCategories = new ArrayList<Category>();
		List<Category> categories = mongoTemplate.find(new Query(Criteria.where("categoryId").in(categoryIds)), Category.class);
		
		for (Category category : categories) {
			Category parentObj = getObject(category.getParentCategory());
			if(parentObj != null){
				parentCategories.add(parentObj);
				 String[] objs = {   parentObj.getCategoryId() };
				getObjectParents(objs);
			}
		}
			return parentCategories;
	}
	
	
	/**
	 * Updates a {@link Category} name for a particular id.
	 */
	public WriteResult updateObject(String id, Category Category) {
		Update update = new Update();
		update.set("name", Category.getName());
		update.set("parentCategory", Category.getParentCategory());

		return mongoTemplate.updateMulti(new Query(Criteria.where("id").is(id)), update, Category.class);
	}

	/**
	 * Delete a {@link Category} for a particular id.
	 */
	public void deleteObject(String categoryId) {
		mongoTemplate.remove(new Query(Criteria.where("categoryId").is(categoryId)), Category.class);
	}

	/**
	 * Create a {@link Category} collection if the collection does not already
	 * exists
	 */
	public void createCollection() {
		if (!mongoTemplate.collectionExists(Category.class)) {
			mongoTemplate.createCollection(Category.class);
		}
	}

	/**
	 * Drops the {@link Category} collection if the collection does already
	 * exists
	 */
	public void dropCollection() {
		if (mongoTemplate.collectionExists(Category.class)) {
			mongoTemplate.dropCollection(Category.class);
		}
	}

	public List<Category> getObjectByCategory(String category) {

		/*
		 * BasicDBObject query = new BasicDBObject("name","John"); DBObject
		 * db_object = findOne("collectionA",query); DBRef myDbRef = new
		 * DBRef(db,"collectionB",db_object); DBObject doc = myDbRef.fetch();
		 * System.out.println(doc);
		 */
		return mongoTemplate.find(new Query(Criteria.where("category").is(category)), Category.class);
	}


}