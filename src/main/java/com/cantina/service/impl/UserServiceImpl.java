package com.cantina.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cantina.model.CantinaCart;
import com.cantina.model.User;
import com.cantina.model.security.Role;
import com.cantina.payload.request.SignupRequest;
import com.cantina.repository.RoleRepository;
import com.cantina.repository.UserRepository;
import com.cantina.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User createUser(SignupRequest user, Set<Role> userRoles) throws Exception {

		User localUser = userRepository.findByUsername(user.getUsername());

		if (localUser != null) {
			//throw new Exception("User already exists. Nothing will be done.");
			LOG.info("User already exists. Nothing will be done.", user.getUsername());

		} else {
			// Create new user's account
			 localUser = new User(user.getUsername(), user.getEmail(),
					encoder.encode(user.getPassword()));
			 System.out.println(user.getPassword());
			
			 for (Role r : userRoles) {
	                roleRepository.save(r);
	            }
			 
			localUser.setRoles(userRoles);
			
			CantinaCart cantinaCart = new CantinaCart();
	        cantinaCart.setUser(localUser);
	        localUser.setCantinaCart(cantinaCart);
			
			userRepository.save(localUser);
		}

		return localUser;

	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

}
