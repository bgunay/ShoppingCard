package com.burhan.webstore.service;

import java.util.List;

import com.burhan.webstore.domain.Cart;

public interface ShoppingCard {

	void addToShoppingCard();
	void removeFromShoppingCard();
	List<Cart> getShoppingCard();
	
}
