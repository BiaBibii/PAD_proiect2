package com.cantina.model.dao;

import java.math.BigDecimal;

public class OrderDao {
	private String userName;
	private BigDecimal orderTotal;
	
	public OrderDao() {
		
	}
	
	public OrderDao(String userName, BigDecimal orderTotal) {
		this.userName = userName;
		this.orderTotal = orderTotal;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	
}
