package com.cantina.repository;

import org.springframework.data.repository.CrudRepository;

import com.cantina.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>{
	
}
