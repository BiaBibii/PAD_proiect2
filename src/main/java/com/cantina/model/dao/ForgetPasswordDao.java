package com.cantina.model.dao;

public class ForgetPasswordDao {
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public ForgetPasswordDao() {
		
	}
	
	public ForgetPasswordDao(String email) {
		this.email = email;
	}
}	
