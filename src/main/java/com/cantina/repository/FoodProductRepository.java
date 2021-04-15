package com.cantina.repository;

import org.springframework.data.repository.CrudRepository;

import com.cantina.model.FoodProduct;

public interface FoodProductRepository extends CrudRepository<FoodProduct, Long> {

}
