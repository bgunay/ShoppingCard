package com.burhan.webstore.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category_product")
public class CategoryProduct {
	
	@Id
	private String id;
	private String categoryId;
	private String productId;
	
	
	public CategoryProduct(String id, String categoryId, String productId) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.productId = productId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "CategoryProduct [id=" + id + ", categoryId=" + categoryId + ", productId=" + productId + "]";
	}

}
