package com.burhan.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.burhan.webstore.domain.Product;

public interface ProductService {
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	Set<Product> getProductsByfilter(Map<String, List<String>> filterParams);
	Product getProductById(String productId);
	void addProduct(Product product);
	public String getFileExtension(String fileName);
}
