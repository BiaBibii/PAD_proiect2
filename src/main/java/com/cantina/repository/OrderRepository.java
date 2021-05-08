package com.cantina.repository;

import org.springframework.data.repository.CrudRepository;

import com.cantina.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
