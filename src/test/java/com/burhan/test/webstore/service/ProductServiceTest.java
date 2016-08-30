package com.burhan.test.webstore.service;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.burhan.webstore.domain.Product;
import com.burhan.webstore.domain.repository.MyMongoRepository;
import com.burhan.webstore.domain.repository.impl.ProductRepositoryImpl;
import com.burhan.webstore.service.ProductService;
import com.burhan.webstore.service.impl.ProductServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class ProductServiceTest {

	@Autowired
	ProductService productService;
	
	@Autowired
	@Qualifier("productRepository")
	private MyMongoRepository<Product> productRepository;

	Product product;

	@Before
	public void setup() {
		product = new Product("56", "testProduct", new BigDecimal(100), "test desc", "apple", "4");
		productRepository.saveObject(product);
		Mockito.when(productRepository.getObject("56")).thenReturn(product);
	}

	@SuppressWarnings("unchecked")
	@After
	public void verify() {
		Mockito.verify(productRepository, VerificationModeFactory.times(1)).getObject(Mockito.anyString());
		// This is allowed here: using container injected mocks
		Mockito.reset(productRepository);
		productRepository.deleteObject(product.getProductId());
	}


	@SuppressWarnings("deprecation")
	@Test()
	public void testProductCallSuccess() {
		Product account = productService.getProductById("56");
		assertEquals("56", product.getProductId());
		assertEquals("testProduct", account.getName());
	}
	@Configuration
	static class AccountServiceTestContextConfiguration {

		@Bean
		public ProductService productService() {
			return new ProductServiceImpl();
		}

		@Bean
		public MyMongoRepository<Product> productRepository() {
			return Mockito.mock(ProductRepositoryImpl.class);
		}
	}
}
