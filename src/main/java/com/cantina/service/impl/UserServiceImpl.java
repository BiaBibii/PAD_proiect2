package com.cantina.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cantina.model.User;
import com.cantina.model.security.UserRole;
import com.cantina.repository.RoleRepository;
import com.cantina.repository.UserRepository;
import com.cantina.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
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
            throw new Exception("User already exists. Nothing will be done."); 
       
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);
        }

        return localUser;
		
		
	}

	
	
	

}
