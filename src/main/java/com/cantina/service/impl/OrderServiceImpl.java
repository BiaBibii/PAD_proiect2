package com.cantina.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantina.model.CantinaCart;
import com.cantina.model.CartItem;
import com.cantina.model.FoodProduct;
import com.cantina.model.Order;
import com.cantina.model.Payment;
import com.cantina.model.User;
import com.cantina.model.UserPayment;
import com.cantina.repository.OrderRepository;
import com.cantina.repository.PaymentRepository;
import com.cantina.service.CartItemService;
import com.cantina.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public synchronized Order createOrder(CantinaCart cantinaCart, UserPayment userPayment, User user) {
		
		Order order = new Order();
		order.setOrderStatus("created");
		Payment payment = new Payment();
		payment.setCardName(userPayment.getCardName());
		payment.setCardNumber(userPayment.getCardNumber());
		payment.setCvc(userPayment.getCvc());
		payment.setExpiryMonth(userPayment.getExpiryMonth());
		payment.setExpiryYear(userPayment.getExpiryYear());
		payment.setHolderName(userPayment.getHolderName());
		payment.setType(userPayment.getType());
		
		order.setPayment(payment);
		
		List<CartItem> cartItemList = cartItemService.findByCantinaCart(cantinaCart);
		
		for(CartItem cartItem : cartItemList) {
			FoodProduct foodProduct  = cartItem.getFoodProduct(); 
			cartItem.setOrder(order);
			foodProduct.setQty(foodProduct.getQty() - cartItem.getQty());
		}
		
		order.setCartItemList(cartItemList);
		order.setOrderTotal(cantinaCart.getGrandTotal());
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		payment = paymentRepository.save(payment);
		
		return order;
	}

	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id).get();
	}

}
