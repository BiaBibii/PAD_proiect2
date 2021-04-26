package com.cantina.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class FoodProduct {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String category;
	private double price;
	private double calories;
	private double protein;
	private double carbohydrates;
	private double fats;
	private double servingWeight;
	
	@Column(columnDefinition="text")
	private String description;
	
	@Transient
	private MultipartFile foodProductImage;
	
	@OneToMany(mappedBy="foodProduct")
	@JsonIgnore
	private List<FoodProductToCartItem> foodProductToCartItem;
	
	public List<FoodProductToCartItem> getFoodProductToCartItem() {
		return foodProductToCartItem;
	}

	public void setFoodProductToCartItem(List<FoodProductToCartItem> foodProductToCartItem) {
		this.foodProductToCartItem = foodProductToCartItem;
	}
	
	private boolean active = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFoodProductImage() {
		return foodProductImage;
	}

	public void setFoodProductImage(MultipartFile bookImage) {
		this.foodProductImage = bookImage;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getServingWeight() {
		return servingWeight;
	}

	public void setServingWeight(double servingWeight) {
		this.servingWeight = servingWeight;
	}
	
}
