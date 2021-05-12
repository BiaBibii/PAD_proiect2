package com.cantina.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cantina.model.CantinaCart;
import com.cantina.model.User;
import com.cantina.model.UserPayment;
import com.cantina.model.security.Role;
import com.cantina.payload.request.SignupRequest;
import com.cantina.repository.RoleRepository;
import com.cantina.repository.UserPaymentRepository;
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
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
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
					encoder.encode(user.getPassword()), user.getFirstName(), user.getLastName());
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
	
	@Override
	public void setUserDefaultPayment(UserPayment userPayment, User user) {
		List<UserPayment> userPaymentList = user.getUserPaymentList();

		for (UserPayment up : userPaymentList) {
			if (up.getId() == userPayment.getId()) {
				up.setDefaultPayment(true);
				userPaymentRepository.save(up);
			} else {
				up.setDefaultPayment(false);
				userPaymentRepository.save(up);
			}
		}

	}

}
