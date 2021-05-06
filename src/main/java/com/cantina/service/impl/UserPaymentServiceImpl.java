package com.cantina.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantina.model.FoodProduct;
import com.cantina.model.UserPayment;
import com.cantina.repository.UserPaymentRepository;
import com.cantina.service.UserPaymentService;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {

	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	@Override
	public UserPayment findById(Long id) {
		return userPaymentRepository.findById(id).get();
	}

	@Override
	public void removeById(Long id) {
		userPaymentRepository.deleteById(id);
	}
	
	@Override
	public UserPayment save(UserPayment userPayment) { 
		return userPaymentRepository.save(userPayment);
	}

}
