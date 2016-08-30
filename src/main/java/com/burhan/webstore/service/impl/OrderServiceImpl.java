package com.burhan.webstore.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.burhan.webstore.domain.Product;
import com.burhan.webstore.domain.repository.MyMongoRepository;
import com.burhan.webstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	@Qualifier("productRepository")
	private MyMongoRepository<Product> mongoRepository;

	public void processOrder(String productId, long quantity) {
		Product productById = mongoRepository.getObject(productId);

		if(productById.getUnitsInStock() < quantity){
			throw new IllegalArgumentException("Out of stock. Available units in stock is "+ productById.getUnitsInStock());
		}
		productById.setUnitsInStock(productById.getUnitsInStock()-quantity);
	}
}
