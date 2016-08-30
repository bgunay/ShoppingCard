package com.burhan.webstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.burhan.webstore.domain.Category;
import com.burhan.webstore.domain.Product;
import com.burhan.webstore.domain.repository.MyMongoRepository;
import com.burhan.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	@Qualifier("productRepository")
	private MyMongoRepository<Product> productRepository;

	@Autowired
	@Qualifier("categoryRepository")
	private MyMongoRepository<Category> categoryRepository;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllObjects();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.getObjectByCategory(category);
	}

	@Override
	public Product getProductById(String productId) {
		return productRepository.getObject(productId);
	}

	@Override
	public void addProduct(Product product) {
		productRepository.saveObject(product);
	}

	public String getFileExtension(String fileName) {

		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	@Override
	public Set<Product> getProductsByfilter(Map<String, List<String>> filterParams) {
		// TODO Auto-generated method stub
		return null;
	}
}
