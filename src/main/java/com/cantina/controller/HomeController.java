package com.cantina.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cantina.model.CantinaCart;
import com.cantina.model.User;
import com.cantina.model.security.ERole;
import com.cantina.model.security.Role;
import com.cantina.payload.request.LoginRequest;
import com.cantina.payload.request.SignupRequest;
import com.cantina.payload.response.JwtResponse;
import com.cantina.payload.response.MessageResponse;
import com.cantina.repository.RoleRepository;
import com.cantina.repository.UserRepository;
import com.cantina.security.jwt.JwtUtils;
import com.cantina.security.services.UserDetailsImpl;
import com.cantina.service.UserService;
import com.cantina.utility.MailConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api")
public class HomeController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";

		// "User is logged out";
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		
		CantinaCart cantinaCart = new CantinaCart();
        cantinaCart.setUser(user);
        user.setCantinaCart(cantinaCart);
		
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	/*
	 * @PostMapping("/forgetPassword") public String
	 * forgetPassword(HttpServletRequest request, @RequestBody String email) throws
	 * Exception {
	 * 
	 * User user = userService.findByEmail(email);
	 * 
	 * if (user == null) { throw new Exception("This user doesn't exist!"); }
	 * 
	 * String password = SecurityUtility.randomPassword(); String encryptedPassword
	 * = SecurityUtility.passwordEncoder().encode(password);
	 * user.setPassword(encryptedPassword);
	 * 
	 * userService.saveUser(user);
	 * 
	 * String appUrl = "http://" + request.getServerName() + ":" +
	 * request.getServerPort() + request.getContextPath();
	 * 
	 * SimpleMailMessage newEmail = mailConstructor.constructResetPassword(appUrl,
	 * request.getLocale(), user, password);
	 * 
	 * mailSender.send(newEmail);
	 * 
	 * return "email send";
	 * 
	 * }
	 */

	/*
	 * @PostMapping("/updateUserInformation") public User passwordReset(@RequestBody
	 * UserDao user, Principal principal) throws Exception {
	 * 
	 * User currentUser = userService.findByUsername(principal.getName());
	 * 
	 * if(currentUser == null) { throw new Exception("User not found"); }
	 * 
	 * check email already exists if(userService.findByEmail(user.getEmail()) !=
	 * null) { if(userService.findByEmail(user.getEmail()).getId() !=
	 * currentUser.getId()) { throw new Exception("Email already exists!"); } }
	 * 
	 * check username already exists
	 * if(userService.findByUsername(user.getUsername()) != null) {
	 * if(userService.findByUsername(user.getUsername()).getId() !=
	 * currentUser.getId()) { throw new Exception("Username already exists!"); } }
	 * 
	 * update password if(user.getPassword() != null &&
	 * !user.getPassword().isEmpty() && !user.getPassword().equals("")) {
	 * BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
	 * currentUser.setPassword(passwordEncoder.encode(user.getPassword())); }
	 * 
	 * currentUser.setFirstName(user.getFirstName());
	 * currentUser.setLastName(user.getLastName());
	 * currentUser.setUsername(user.getUsername());
	 * currentUser.setEmail(user.getEmail());
	 * 
	 * currentUser = userService.saveUser(currentUser);
	 * 
	 * UserDetails userDetails =
	 * userSecurityService.loadUserByUsername(currentUser.getUsername());
	 * 
	 * Authentication authentication = new
	 * UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
	 * userDetails.getAuthorities());
	 * 
	 * SecurityContextHolder.getContext().setAuthentication(authentication);
	 * 
	 * return currentUser; }
	 */

}
