package com.cantina.service;

import java.util.Set;

import com.cantina.model.User;
import com.cantina.model.security.UserRole;

public interface UserService {

	public User findByEmail(String email);
	public User saveUser(User user);
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
}
