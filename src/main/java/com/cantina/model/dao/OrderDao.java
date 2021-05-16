package com.cantina.model.dao;

import java.math.BigDecimal;
import java.util.List;

public class OrderDao {
	private String userName;
	private BigDecimal orderTotal;
	private List<CartItemDao> cartItemDaoList;
	
	public OrderDao() {
		
	}
	
	public OrderDao(String userName, BigDecimal orderTotal, List<CartItemDao> cartItemDaoList) {
		this.userName = userName;
		this.orderTotal = orderTotal;
		this.cartItemDaoList = cartItemDaoList;
	}

	public List<CartItemDao> getCartItemDaoList() {
		return cartItemDaoList;
	}

	public void setCartItemDaoList(List<CartItemDao> cartItemDaoList) {
		this.cartItemDaoList = cartItemDaoList;
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
