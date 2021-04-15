package com.cantina.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int qty;
	private BigDecimal subTotal;
	
	@OneToOne
	private FoodProduct foodProduct;
	
	@OneToMany(mappedBy="cartItem")
	@JsonIgnore
	private List<FoodProductToCartItem> foodProductToCartItem;
	
	@ManyToOne
	@JoinColumn(name="cantina_cart_id")
	private CantinaCart cantinaCart;
	
	

	public FoodProduct getFoodProduct() {
		return foodProduct;
	}

	public void setFoodProduct(FoodProduct foodProduct) {
		this.foodProduct = foodProduct;
	}

	public List<FoodProductToCartItem> getFoodProductToCartItem() {
		return foodProductToCartItem;
	}

	public void setFoodProductToCartItem(List<FoodProductToCartItem> foodProductToCartItem) {
		this.foodProductToCartItem = foodProductToCartItem;
	}

	public CantinaCart getCantinaCart() {
		return cantinaCart;
	}

	public void setCantinaCart(CantinaCart cantinaCart) {
		this.cantinaCart = cantinaCart;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	

	

	
}
