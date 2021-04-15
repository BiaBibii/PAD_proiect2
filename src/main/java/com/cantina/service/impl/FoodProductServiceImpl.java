package com.cantina.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantina.model.FoodProduct;
import com.cantina.repository.FoodProductRepository;
import com.cantina.service.FoodProductService;

@Service
public class FoodProductServiceImpl implements FoodProductService {

	@Autowired
	private FoodProductRepository foodProductRepository;
	
	@Override
	public List<FoodProduct> findAll() {
		List<FoodProduct> foodList = (List<FoodProduct>) foodProductRepository.findAll();
		return foodList;
	}

	@Override
	public FoodProduct save(FoodProduct foodProduct) { 
		return foodProductRepository.save(foodProduct);
	}

	@Override
	public FoodProduct findById(Long id) {
		return foodProductRepository.findById(id).get();
	}

	@Override
	public void delete(FoodProduct foodProduct) {
		 foodProductRepository.delete(foodProduct);
	}
	
	

}
