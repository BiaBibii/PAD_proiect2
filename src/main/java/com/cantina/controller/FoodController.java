package com.cantina.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cantina.model.FoodProduct;
import com.cantina.model.ImageModel;
import com.cantina.model.dao.FoodProductDao;
import com.cantina.repository.ImageRepository;
import com.cantina.service.FoodProductService;
import com.cantina.service.ImageService;


@RestController
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping("/api/foods")
public class FoodController {
	
	@Autowired
	private FoodProductService foodProductService;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ImageService imageService;
	
	
	@GetMapping("/foodList")
	public List<FoodProduct> getAllFoodProducts(){
		List<FoodProduct> foodList = foodProductService.findAll();
		return foodList;
	}
	
	@PostMapping("/addFoodProduct")
	public FoodProduct addFoodProduct(@RequestBody FoodProductDao foodProduct) throws Exception {
		
		FoodProduct newFoodProduct = new FoodProduct();
		newFoodProduct.setTitle(foodProduct.getTitle());
		newFoodProduct.setCategory(foodProduct.getCategory());
		newFoodProduct.setPrice(foodProduct.getPrice());
		newFoodProduct.setCalories(foodProduct.getCalories());
		newFoodProduct.setProtein(foodProduct.getProtein());
		newFoodProduct.setCarbohydrates(foodProduct.getCarbohydrates());
		newFoodProduct.setFats(foodProduct.getFats());
		newFoodProduct.setQty(foodProduct.getQty());
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



		@GetMapping("/getFoodProduct/{id}")
	public FoodProduct getFoodProduct(@PathVariable(value = "id") Long id) throws Exception {
		
		
		FoodProduct foodProduct = foodProductService.findById(id);
		
		return foodProduct;
	}
	
	//pune hidden id
	@PostMapping("/updateFoodProduct/{id}")
	public FoodProduct updateFoodProductPost(@RequestBody FoodProductDao foodProduct, @PathVariable("id") Long id) throws Exception {
		
		FoodProduct updatedFoodProduct = foodProductService.findById(id);
		
		if(updatedFoodProduct == null) {
			throw new Exception("This food product does not exist!");
		}
		
		updatedFoodProduct.setTitle(foodProduct.getTitle());
		updatedFoodProduct.setCategory(foodProduct.getCategory());
		updatedFoodProduct.setPrice(foodProduct.getPrice());
		updatedFoodProduct.setCalories(foodProduct.getCalories());
		updatedFoodProduct.setProtein(foodProduct.getProtein());
		updatedFoodProduct.setCarbohydrates(foodProduct.getCarbohydrates());
		updatedFoodProduct.setFats(foodProduct.getFats());
		updatedFoodProduct.setQty(foodProduct.getQty());
		updatedFoodProduct.setServingWeight(foodProduct.getServingWeight());
		updatedFoodProduct.setDescription(foodProduct.getDescription());
		
		updatedFoodProduct = foodProductService.save(updatedFoodProduct);
		
		/*MultipartFile foodImage = foodProduct.getFoodProductImage();
		
		if(foodImage != null) {
			try {
				byte[] bytes = foodImage.getBytes();
				String name = String.valueOf(id) + ".png";
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/images/food/" + name)));
				stream.write(bytes);
				stream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}*/ 
		
		return updatedFoodProduct;
		
	}
	
	@DeleteMapping("/deleteFoodProduct/{id}")
	public FoodProduct deleteFoodProduct(@PathVariable("id") Long id) throws Exception {
		
		FoodProduct foodProduct = foodProductService.findById(id);
		
		if(foodProduct == null) {
			throw new Exception("Food Product not found. Nothing to delete!");
		}
		
		foodProductService.delete(foodProduct);
		
		return foodProduct;
	}
	
	@PostMapping("/addImage")
	public ImageModel addImage(@RequestParam("file") MultipartFile file) throws IOException {
		
		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
		
		final ImageModel savedImage = imageRepository.save(img);
		
		return savedImage;
	}
	
	@GetMapping(path = { "/get/{id}" })
	public ImageModel getImage(@PathVariable("id") Long id) throws Exception {
	    
		final Optional<ImageModel> retrievedImage = imageRepository.findById(id);
	     
		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(), retrievedImage.get().getPic());
			
			return img;
	    }
	
	
}
