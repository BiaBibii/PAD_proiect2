package com.cantina.model.dao;

public class CartItemDao {

	private Integer qty;
	private String productName;
	
	public CartItemDao() {}
	
	public CartItemDao(Integer qty, String productName) {
		this.qty = qty;
		this.productName = productName;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
