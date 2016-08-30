package com.burhan.test.webstore.service;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.burhan.webstore.config.SpringMongoConfig;
import com.burhan.webstore.domain.Cart;
import com.burhan.webstore.domain.CartItem;
import com.burhan.webstore.domain.Product;
import com.burhan.webstore.service.CartService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringMongoConfig.class  })
public class CartServiceTest {

	@Autowired
	CartService cartService;
	
	private Product product;
	private Cart cart;

	@Before
	public void dataSetup() {
		Product product;
		cart = new Cart();
		cart.setCartId("1");
		for (int i = 1; i < 11; i++) {
			product = new Product("100" + i , "product" + i, new BigDecimal(i * 10) );
			cart.addCartItem(new CartItem(product));
		}
		cartService.create(cart);
	}
	
	@After
	public void verify() {
		cartService.delete(cart.getCartId());
	}

		

	@SuppressWarnings("deprecation")
	@Test
	public void testReadQuery() {
		Cart result = cartService.read("1");
		assertEquals(10, result.getCartItems().size());
		assertEquals("product1", result.getCartItems().get("1001").getProduct().getName());
		assertEquals(new BigDecimal(10), result.getCartItems().get("1001").getProduct().getUnitPrice());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testCreateQuery() {
		Cart _cart = new Cart("555");
		product = new Product("150", "productXYZ", new BigDecimal(200));
		_cart.addCartItem(new CartItem(product));
		cartService.create(_cart);
		assertEquals(10, cartService.read("1").getCartItems().size());
		assertEquals(1, cartService.read("555").getCartItems().size());
		assertEquals("productXYZ", cartService.read("555").getCartItems().get("150").getProduct().getName());
		cartService.delete(_cart.getCartId());
	}
/*
	@Test
	public void testDeleteQuery() {
		 
	}

	@Test
	public void testUpdateQuery() {
		assertEquals(1, 1);
	}
*/
}
