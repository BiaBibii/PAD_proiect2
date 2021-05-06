package com.cantina.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantina.model.CantinaCart;
import com.cantina.model.CartItem;
import com.cantina.repository.CantinaCartRepository;
import com.cantina.service.CantinaCartService;
import com.cantina.service.CartItemService;

@Service
public class CantinaCartServiceImpl implements CantinaCartService {
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private CantinaCartRepository cantinaCartRepository;

	@Override
	public CantinaCart updateCantinaCart(CantinaCart cantinaCart) {
		
		BigDecimal cartTotal = new BigDecimal(0);
		
		List<CartItem> cartItemList = cartItemService.findByCantinaCart(cantinaCart);
		
		for(CartItem cartItem : cartItemList) {
			cartItemService.updateCartItem(cartItem);
			cartTotal = cartTotal.add(cartItem.getSubTotal());
		}
		
		cantinaCart.setGrandTotal(cartTotal);
		cantinaCartRepository.save(cantinaCart);
		return cantinaCart;
		
	}
	
	@Override
	public void clearCantinaCart(CantinaCart cantinaCart) {
		List<CartItem> cartItemList = cartItemService.findByCantinaCart(cantinaCart);		
		for (CartItem cartItem : cartItemList) {
			cartItem.setCantinaCart(null);
			cartItemService.save(cartItem);
		}
		
		cantinaCart.setGrandTotal(new BigDecimal(0));
		
		cantinaCartRepository.save(cantinaCart);
	}

}
