package com.burhan.webstore.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
public class Category {

	@Id
	private String categoryId;
	private String name;
	private String parentCategory;
	
	
	public Category(String categoryId, String name, String parentCategory) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.parentCategory = parentCategory;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
	
	@Override
	public String toString() {
		return "{ label: '"+ name + "'}";
	}
	
	
}
