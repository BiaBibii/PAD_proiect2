package com.cantina.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantina.model.CantinaCart;
import com.cantina.model.User;
import com.cantina.model.security.UserRole;
import com.cantina.repository.RoleRepository;
import com.cantina.repository.UserRepository;
import com.cantina.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
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
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
        	/* throw new Exception("User already exists. Nothing will be done."); */
			LOG.info("User already exists. Nothing will be done.", user.getUsername());
       
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            
            CantinaCart cantinaCart = new CantinaCart();
            cantinaCart.setUser(user);
			user.setCantinaCart(cantinaCart);

            localUser = userRepository.save(user);
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