package com.cantina.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cantina.model.User;
import com.cantina.model.security.Role;
import com.cantina.model.security.UserRole;
import com.cantina.service.UserService;
import com.cantina.utility.SecurityUtility;

@RestController
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/api")  
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) throws Exception {
		
		String tempEmail = user.getEmail();
		
		if(!tempEmail.equals("") && tempEmail != null) {
			User newUser = userService.findByEmail(tempEmail);
			if(newUser != null)
				throw new Exception("User "+ newUser.getEmail() + " already exists!");
		}
		
		User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());

         
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(user.getPassword());
        newUser.setPassword(encryptedPassword);

        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        newUser = userService.createUser(user, userRoles);
		
		
		return newUser;
		
	}
	
	

}


