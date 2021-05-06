package com.cantina.service;

import com.cantina.model.FoodProduct;
import com.cantina.model.UserPayment;

public interface UserPaymentService {
	UserPayment findById(Long id);
	void removeById(Long id);
	public UserPayment save(UserPayment userPayment);
}
