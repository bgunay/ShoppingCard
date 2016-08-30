package com.burhan.webstore.service;


import com.burhan.webstore.domain.Cart;

public interface CartService {
	Cart create(Cart cart);
	Cart read(String cartId);
	void update(String cartId, Cart cart);
	void delete(String cartId);
}
