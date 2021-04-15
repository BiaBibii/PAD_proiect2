package com.cantina.service;

import java.util.List;

import com.cantina.model.FoodProduct;

public interface FoodProductService {
	
	public List<FoodProduct> findAll();
	public FoodProduct save(FoodProduct foodProduct);
	public FoodProduct findById(Long id);
	public void delete(FoodProduct foodProduct);
}
