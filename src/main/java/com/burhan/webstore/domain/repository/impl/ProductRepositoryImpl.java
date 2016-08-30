package com.burhan.webstore.domain.repository.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.burhan.webstore.domain.Category;
import com.burhan.webstore.domain.CategoryProduct;
import com.burhan.webstore.domain.Product;
import com.burhan.webstore.domain.repository.CatogeryOperations;
import com.burhan.webstore.domain.repository.MyMongoRepository;
import com.burhan.webstore.domain.repository.SequenceGenerator;
import com.burhan.webstore.util.Util;
import com.mongodb.WriteResult;

@Repository(value = "productRepository")
public class ProductRepositoryImpl implements MyMongoRepository<Product> {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	GridFsTemplate gridFsTemplate;

	@Autowired
	SequenceGenerator seqGenerator;

	@Autowired
	@Qualifier("categoryRepository")
	private MyMongoRepository<Category> categoryRepository;

	@Autowired
	@Qualifier("categoryRepository")
	private CatogeryOperations catogeryOperations;

	@Autowired
	Util util;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * Get all Products.
	 */
	public List<Product> getAllObjects() {
		return mongoTemplate.findAll(Product.class);
	}

	/**
	 * Saves a {@link Product}.
	 * 
	 * @throws IOException
	 */
	public void saveObject(Product product) {
		if (product.getProductId().isEmpty())
			product.setProductId(seqGenerator.getNextSequence("productId"));
		mongoTemplate.insert(product);
		String category = product.getCategory();
		if (category.length() > 0) {
			// if product has more category
			String[] categories = product.getCategory().split(",");
			if (categories.length > 0) {
				insertProductCategory(product, categories);
			} else {
				categories = new String[1];
				categories[0] = category;
				insertProductCategory(product, categories);
			}

		}
	}

	/**
	 * Gets a {@link Product} for a particular id.
	 */
	public Product getObject(String productId) {
		return mongoTemplate.findOne(new Query(Criteria.where("productId").is(productId)), Product.class);
	}

	/**
	 * Updates a {@link Product} name for a particular id.
	 */
	public WriteResult updateObject(String id, Product product) {
		Update update = new Update();
		update.set("name", product.getName());
		update.set("condition", product.getCondition());
		update.set("description", product.getDescription());
		update.set("manufacturer", product.getManufacturer());
		update.set("imageSource", product.getImageSource());
		update.set("unitPrice", product.getUnitPrice());
		update.set("unitsInOrder", product.getUnitsInOrder());
		update.set("unitsInStock", product.getUnitsInStock());

		return mongoTemplate.updateMulti(new Query(Criteria.where("id").is(id)), update, Product.class);
	}

	/**
	 * Delete a {@link Product} for a particular id.
	 */
	public void deleteObject(String id) {
		mongoTemplate.remove(new Query(Criteria.where("productId").is(id)), Product.class);
	}

	/**
	 * Create a {@link Product} collection if the collection does not already
	 * exists
	 */
	public void createCollection() {
		if (!mongoTemplate.collectionExists(Product.class)) {
			mongoTemplate.createCollection(Product.class);
		}
	}

	/**
	 * Drops the {@link Product} collection if the collection does already
	 * exists
	 */
	public void dropCollection() {
		if (mongoTemplate.collectionExists(Product.class)) {
			mongoTemplate.dropCollection(Product.class);
		}
	}

	public List<Product> getObjectByCategory(String category) {
		List<Category> childCategories = catogeryOperations.getObjectChilds(category);
		List<Product> products = new ArrayList<Product>();
		List<CategoryProduct> childCategoryProducts = null;
		List<String> productIdList = new ArrayList<String>();
		// get main category products
		List<CategoryProduct> categorProducts = mongoTemplate.find(new Query(Criteria.where("categoryId").in(category)),
				CategoryProduct.class);

		for (Category childCategory : childCategories) {
			childCategoryProducts = mongoTemplate.find(
					new Query(Criteria.where("categoryId").in(childCategory.getCategoryId())), CategoryProduct.class);
			categorProducts.addAll(childCategoryProducts);
		}
		for (CategoryProduct _cp : categorProducts) {
			productIdList.add(_cp.getProductId());
		}

		productIdList = Util.removeDuplicates(productIdList);
		String[] productIdArray = new String[productIdList.size()];
		productIdArray = productIdList.toArray(productIdArray);

		products = mongoTemplate.find(new Query(Criteria.where("productId").in(productIdList)), Product.class);
		return products;
	}

	private void insertProductCategory(Product product, String[] categories) {
		List<String> categoryIdList = new ArrayList<String>();

		List<Category> parentCategories = catogeryOperations.getObjectParents(categories);
		for (Category category : parentCategories) {
			categoryIdList.add(category.getCategoryId());
		}
		try {
			categoryIdList.addAll(Arrays.asList(categories));
			categoryIdList = Util.removeDuplicates(categoryIdList);
			for (String categoryId : categoryIdList) {
				mongoTemplate.insert(new CategoryProduct(seqGenerator.getNextSequence("categoryProductId").toString(),
						categoryId, product.getProductId()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}