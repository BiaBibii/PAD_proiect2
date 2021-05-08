package com.cantina.model.dao;

import org.springframework.web.multipart.MultipartFile;

public class FoodProductDao {
	
	private String title;
	private String category;
	private double price;
	private double calories;
	private double protein;
	private double carbohydrates;
	private double fats;
	private double servingWeight;
	private int qty;
	
	private MultipartFile foodProductImage;
	
	private String description;
	
	public FoodProductDao() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public double getFats() {
		return fats;
	}

	public void setFats(double fats) {
		this.fats = fats;
	}
	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getServingWeight() {
		return servingWeight;
	}

	public void setServingWeight(double servingWeight) {
		this.servingWeight = servingWeight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FoodProductDao(String title, String category, double price, double calories, double protein,
			double carbohydrates, double fats, double servingWeight, String description) {
		super();
		this.title = title;
		this.category = category;
		this.price = price;
		this.calories = calories;
		this.protein = protein;
		this.carbohydrates = carbohydrates;
		this.fats = fats;
		this.servingWeight = servingWeight;
		this.description = description;
	}

	public MultipartFile getFoodProductImage() {
		return foodProductImage;
	}

	public void setFoodProductImage(MultipartFile foodProductImage) {
		this.foodProductImage = foodProductImage;
	}
	
	
}
