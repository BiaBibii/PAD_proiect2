package com.cantina.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cantina.model.FoodProduct;
import com.cantina.model.User;
import com.cantina.model.dao.FoodProductDao;
import com.cantina.model.security.Role;
import com.cantina.model.security.UserRole;
import com.cantina.service.FoodProductService;
import com.cantina.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping("/api/foods")
public class FoodController {
	
	@Autowired
	private FoodProductService foodProductService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/foodList")
	public List<FoodProduct> getAllFoodProducts(){
		List<FoodProduct> foodList = foodProductService.findAll();
		return foodList;
	}
	
	@PostMapping("/addFoodProduct")
	public FoodProduct addFoodProduct(@RequestBody FoodProductDao foodProduct, Principal principal) throws Exception {
		
		FoodProduct newFoodProduct = new FoodProduct();
		newFoodProduct.setTitle(foodProduct.getTitle());
		newFoodProduct.setCategory(foodProduct.getCategory());
		newFoodProduct.setPrice(foodProduct.getPrice());
		newFoodProduct.setCalories(foodProduct.getCalories());
		newFoodProduct.setProtein(foodProduct.getProtein());
		newFoodProduct.setCarbohydrates(foodProduct.getCarbohydrates());
		newFoodProduct.setFats(foodProduct.getFats());
		newFoodProduct.setServingWeight(foodProduct.getServingWeight());
		newFoodProduct.setDescription(foodProduct.getDescription());
		
		newFoodProduct = foodProductService.save(newFoodProduct);
		
		MultipartFile foodImage = foodProduct.getFoodProductImage();
		
		if(foodImage != null) {
			try {
				byte[] bytes = foodImage.getBytes();
				String name = newFoodProduct.getId() + ".png";
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/food/" + name)));
				stream.write(bytes);
				stream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return newFoodProduct;
		
	}
	
	@GetMapping("/updateFoodProduct/{id}")
	public FoodProduct updateFoodProduct(@PathVariable(value = "id") Long id, Principal principal) throws Exception {
		
		
		FoodProduct foodProduct = foodProductService.findById(id);
		
		return foodProduct;
	}
	
	//pune hidden id
	@PostMapping("/updateFoodProduct")
	public FoodProduct updateFoodProductPost(@RequestBody FoodProduct foodProduct, Principal principal) throws Exception {
		
		FoodProduct updatedFoodProduct = foodProductService.save(foodProduct);
		
		MultipartFile foodImage = foodProduct.getFoodProductImage();
		
		if(foodImage != null) {
			try {
				byte[] bytes = foodImage.getBytes();
				String name = foodProduct.getId() + ".png";
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/food/" + name)));
				stream.write(bytes);
				stream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return updatedFoodProduct;
		
	}
	
	@DeleteMapping("/deleteFoodProduct/{id}")
	public FoodProduct deleteFoodProduct(@PathVariable(value = "id") Long id, Principal principal) throws Exception {
		
		FoodProduct foodProduct = foodProductService.findById(id);
		
		if(foodProduct == null) {
			throw new Exception("Food Product not found. Nothing to delete!");
		}
		
		foodProductService.delete(foodProduct);
		
		return foodProduct;
	}
	
}
