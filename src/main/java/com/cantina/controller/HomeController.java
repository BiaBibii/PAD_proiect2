package com.cantina.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cantina.model.User;
import com.cantina.model.dao.UserDao;
import com.cantina.model.security.Role;
import com.cantina.model.security.UserRole;
import com.cantina.service.UserService;
import com.cantina.service.impl.UserSecurityService;
import com.cantina.utility.MailConstructor;
import com.cantina.utility.SecurityUtility;

@RestController
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/api")  
public class HomeController {
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody UserDao user) throws Exception {
		
		String tempEmail = user.getEmail();
		String tempUsername = user.getUsername();
		
		if(!tempEmail.equals("") && tempEmail != null) {
			User newUser = userService.findByEmail(tempEmail);
			if(newUser != null)
				throw new Exception("User "+ newUser.getEmail() + " already exists!");
		}
		
		if(!tempUsername.equals("") && tempUsername != null) {
			User newUser = userService.findByUsername(tempUsername);
			if(newUser != null)
				throw new Exception("User "+ newUser.getUsername() + " already exists!");
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
        userRoles.add(new UserRole(newUser, role));
        newUser = userService.createUser(newUser, userRoles);
		
		return newUser;
		
	}
	
	@PostMapping("/login")
	public User login(@RequestBody UserDao user) throws Exception {
		
		User userToLogin = userService.findByUsername(user.getUsername());
		
		if(userToLogin == null) {
			throw new Exception("User " + user.getUsername() + " doesn't exist. Go and create a new account!");
		}
		
		else {
			
			String username = userToLogin.getUsername();
			
			BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			
			if(!passwordEncoder.matches(user.getPassword(), userToLogin.getPassword())) {
				
				throw new Exception("Password is incorrect!");
				
			} else {
			
				System.out.println(username);
		
				// make sure that the current login session is for the user.
				UserDetails userDetails = userSecurityService.loadUserByUsername(username);
		
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
						userDetails.getAuthorities());
		
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				return userToLogin;
				
			}
		}
	}
	
	@RequestMapping(value="/logout")
	public String logout (HttpServletRequest request, HttpServletResponse response) {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (auth != null){
	    new SecurityContextLogoutHandler().logout(request, response, auth);
	  }
	  return "redirect:/login?logout";
			  
			  //"User is logged out";
	}
	
	@PostMapping("/forgetPassword")
	public String forgetPassword(HttpServletRequest request, @RequestBody String email) throws Exception {

		User user = userService.findByEmail(email);
    
		if (user == null) {
			throw new Exception("This user doesn't exist!");
		}

		String password = SecurityUtility.randomPassword();
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);

		userService.saveUser(user);

		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		SimpleMailMessage newEmail = mailConstructor.constructResetPassword(appUrl, request.getLocale(), user,
				password);

		mailSender.send(newEmail);

		return "email send";

	}
	
	@PostMapping("/updateUserInformation")
	public User passwordReset(@RequestBody UserDao user, Principal principal) throws Exception {
		
		User currentUser = userService.findByUsername(principal.getName());
		
		if(currentUser == null) {
			throw new Exception("User not found");
		}
		
		/* check email already exists */
		if(userService.findByEmail(user.getEmail()) != null) {
			if(userService.findByEmail(user.getEmail()).getId() != currentUser.getId()) {
				throw new Exception("Email already exists!");	
			}
		}
		
		/* check username already exists */
		if(userService.findByUsername(user.getUsername()) != null) {
			if(userService.findByUsername(user.getUsername()).getId() != currentUser.getId()) {
				throw new Exception("Username already exists!"); 
			}
		}
		
		/* update password */
		if(user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().equals("")) {
			BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setUsername(user.getUsername());
		currentUser.setEmail(user.getEmail());
		
		currentUser = userService.saveUser(currentUser);
		
		UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return currentUser;
	}
	
	
}
