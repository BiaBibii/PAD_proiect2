package com.cantina.service;

import java.util.List;

import com.cantina.model.CantinaCart;
import com.cantina.model.Order;
import com.cantina.model.User;
import com.cantina.model.UserPayment;

public interface OrderService {
	Order createOrder(CantinaCart shoppingCart, UserPayment userPayment, User user);
	Order findById(Long id);
	List<Order> getOrderList();
}
