package com.burhan.test.webstore.repository;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.burhan.webstore.config.SpringMongoConfig;
import com.burhan.webstore.domain.Product;
import com.burhan.webstore.domain.repository.MyMongoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringMongoConfig.class  })
@Transactional
public class ProductRepositoryTest {

	@Autowired
	@Qualifier("productRepository")
	private MyMongoRepository<Product> productRepository;

	Product product;
	
	@Before
	public void setupData() {
		product = new Product("56", "testProduct", new BigDecimal(100),"test desc", "apple", "4");
		productRepository.saveObject(product);
	}
	
	
	@After
	public void tearDown() {
		productRepository.deleteObject(product.getProductId());
	}
	
	
	@Test
	public void testGetProductById() {
		Product product = productRepository.getObject(this.product.getProductId());
		assertEquals(this.product.getName(), product.getName());
		assertEquals(this.product.getUnitPrice(), product.getUnitPrice());
		productRepository.deleteObject(product.getProductId());
	}
	
	
	

}
