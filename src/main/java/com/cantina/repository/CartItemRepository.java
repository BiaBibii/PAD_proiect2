package com.cantina.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cantina.model.CantinaCart;
import com.cantina.model.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
	List<CartItem> findByCantinaCart(CantinaCart cantinaCart);
}
