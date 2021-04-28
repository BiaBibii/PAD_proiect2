package com.cantina.utility;

public class UserResetPassword {
	
	private String email;
	private String password;
	private String newPassword;
	private String confirmNewPassword;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	public UserResetPassword(String email, String password, String newPassword, String confirmNewPassword) {
		super();
		this.email = email;
		this.password = password;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}
	
	public UserResetPassword() {
		
	}
	
	public String toString() {
		return email + " " + password + " " + newPassword + " " + confirmNewPassword;
	}

}
