package com.burhan.webstore.domain.repository.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;

import com.burhan.webstore.domain.*;
import com.burhan.webstore.domain.repository.MyMongoRepository;

@Repository
@Component(value = "cartRepository")
public class CartRepositoryImpl implements MyMongoRepository<Cart> {

	MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * Get all Carts.
	 */
	public List<Cart> getAllObjects() {
		return mongoTemplate.findAll(Cart.class);
	}

	/**
	 * Saves a {@link Cart}.
	 */
	public void saveObject(Cart cart) {
		mongoTemplate.insert(cart);
	}

	/**
	 * Gets a {@link Cart} for a particular id.
	 */
	public Cart getObject(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Cart.class);
	}

	/**
	 * Updates a {@link Cart} name for a particular id.
	 */
	public WriteResult updateObject(String id, Cart cart) {
		return mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), 
				Update.update("grandTotal", cart.getGrandTotal()), Cart.class);
	}

	/**
	 * Delete a {@link Cart} for a particular id.
	 */
	public void deleteObject(String id) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Cart.class);
	}

	/**
	 * Create a {@link Cart} collection if the collection does not already
	 * exists
	 */
	public void createCollection() {
		if (!mongoTemplate.collectionExists(Cart.class)) {
			mongoTemplate.createCollection(Cart.class);
		}
	}

	/**
	 * Drops the {@link Cart} collection if the collection does already exists
	 */
	public void dropCollection() {
		if (mongoTemplate.collectionExists(Cart.class)) {
			mongoTemplate.dropCollection(Cart.class);
		}
	}
	
}