package com.cantina.service;

import java.util.Set;

import com.cantina.model.User;
import com.cantina.model.UserPayment;
import com.cantina.model.security.Role;
import com.cantina.payload.request.SignupRequest;


public interface UserService {

	public User findByEmail(String email);
	public User saveUser(User user);
	public User createUser(SignupRequest user,Set<Role> userRoles) throws Exception;
	public User findByUsername(String username);
	public User findById(Long id);
	void setUserDefaultPayment(UserPayment userPayment, User user);
	
}
