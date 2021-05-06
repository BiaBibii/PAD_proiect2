package com.cantina.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantina.model.CantinaCart;
import com.cantina.model.CartItem;
import com.cantina.model.FoodProduct;
import com.cantina.model.FoodProductToCartItem;
import com.cantina.model.User;
import com.cantina.repository.CartItemRepository;
import com.cantina.repository.FoodProductToCartItemRepository;
import com.cantina.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private FoodProductToCartItemRepository foodProductToCartItemRepository;
	
	@Override
	public List<CartItem> findByCantinaCart(CantinaCart cantinaCart){
		return cartItemRepository.findByCantinaCart(cantinaCart);
	}

	@Override
	public CartItem addProductToCartItem(FoodProduct foodProduct, User user) {
		
		List<CartItem> cartItemList = findByCantinaCart(user.getCantinaCart());
		
		for(CartItem cartItem : cartItemList) {
			if(foodProduct.getId() == cartItem.getFoodProduct().getId()) {
				cartItem.setQty(cartItem.getQty() + 1);
				cartItem.setSubTotal(new BigDecimal(foodProduct.getPrice()).multiply(new BigDecimal(cartItem.getQty())));
				cartItemRepository.save(cartItem);
				return cartItem;
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setCantinaCart(user.getCantinaCart());
		cartItem.setFoodProduct(foodProduct);
		cartItem.setQty(1);
		cartItem.setSubTotal(new BigDecimal(foodProduct.getPrice()).multiply(new BigDecimal(cartItem.getQty())));
		cartItem = cartItemRepository.save(cartItem);
		
		FoodProductToCartItem foodProductToCartItem = new FoodProductToCartItem();
		foodProductToCartItem.setFoodProduct(foodProduct);
		foodProductToCartItem.setCartItem(cartItem);
		foodProductToCartItemRepository.save(foodProductToCartItem);	
		
		return cartItem;
		
	}

	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getFoodProduct().getPrice()).multiply(new BigDecimal(cartItem.getQty()));
		
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubTotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		return cartItem;
	}

	@Override
	public CartItem findById(Long id) {
		return cartItemRepository.findById(id).get();
	}

	@Override
	public void deleteItemFromCart(CartItem cartItem) {
		foodProductToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}
	
	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

}
