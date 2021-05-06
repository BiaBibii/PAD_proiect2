package com.cantina.model.dao;

import java.util.List;

import com.cantina.model.CartItem;
import com.cantina.model.UserPayment;

public class CheckoutDao {

	private String name;
	private List<CartItem> cartItemList;
	private List<UserPayment> userPaymentList;
	
	public CheckoutDao() {}
	
	public CheckoutDao(String name, List<CartItem> cartItemList, List<UserPayment> userPaymentList) {
		this.name = name;
		this.cartItemList = cartItemList;
		this.userPaymentList = userPaymentList;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CartItem> getCartItemList() {
		return cartItemList;
	}
	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}
	public List<UserPayment> getUserPaymentList() {
		return userPaymentList;
	}
	public void setUserPaymentList(List<UserPayment> userPaymentList) {
		this.userPaymentList = userPaymentList;
	}
	
}
