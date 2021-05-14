package com.cantina.service;

import java.util.List;

import com.cantina.model.CantinaCart;
import com.cantina.model.CartItem;
import com.cantina.model.FoodProduct;
import com.cantina.model.User;

public interface CartItemService {
	
	List<CartItem> findByCantinaCart(CantinaCart cantinaCart);
	public CartItem addProductToCartItem(FoodProduct foodProduct, User user) throws Exception;
	public CartItem updateCartItem(CartItem cartItem, int qty) throws Exception;
	public CartItem findById(Long id);
	public void deleteItemFromCart(CartItem cartItem);
	CartItem save(CartItem cartItem);

}
