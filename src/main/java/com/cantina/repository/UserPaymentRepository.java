package com.cantina.repository;

import org.springframework.data.repository.CrudRepository;

import com.cantina.model.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {
		
}
