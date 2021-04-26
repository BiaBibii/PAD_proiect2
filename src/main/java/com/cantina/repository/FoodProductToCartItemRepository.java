package com.cantina.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cantina.model.CartItem;
import com.cantina.model.FoodProductToCartItem;

@Transactional
public interface FoodProductToCartItemRepository extends CrudRepository<FoodProductToCartItem, Long> {
	void deleteByCartItem(CartItem cartItem);
}
