package com.cantina.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cantina.model.CantinaCart;
import com.cantina.model.CartItem;
import com.cantina.model.Order;
import com.cantina.model.Payment;
import com.cantina.model.User;
import com.cantina.model.UserPayment;
import com.cantina.model.dao.CheckoutDao;
import com.cantina.service.CantinaCartService;
import com.cantina.service.CartItemService;
import com.cantina.service.OrderService;
import com.cantina.service.PaymentService;
import com.cantina.service.UserPaymentService;
import com.cantina.service.UserService;
import com.cantina.utility.MailConstructor;

@RestController
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	private Payment payment = new Payment();

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private UserPaymentService userPaymentService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired 
	private CantinaCartService cantinaCartService;
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/goToCheckout")
	public CheckoutDao checkout(Principal principal) throws Exception {

		User user = userService.findByUsername(principal.getName());
		
		if(user == null)
			throw new Exception("User not found");
		
		List<CartItem> cartItemList = cartItemService.findByCantinaCart(user.getCantinaCart());
		List<UserPayment> userPaymentList = user.getUserPaymentList();
		
		CheckoutDao checkoutDao = new CheckoutDao(user.getUsername(), cartItemList, userPaymentList);
		
		
		return checkoutDao;
		
	}
	
	
	
	@PostMapping("/placeCommand")
	public Order checkoutPost(Principal principal) throws Exception {
		
		User user = userService.findByUsername(principal.getName());
		
		if(user == null)
			throw new Exception("User not found");
		
		CantinaCart cantinaCart = user.getCantinaCart();
		List<UserPayment> userPaymentList = user.getUserPaymentList();
		UserPayment userPayment = null;
		
		for(UserPayment up : userPaymentList) {
			if(up.isDefaultPayment() == true) {
				userPayment = up;
				break;
			}	
		}
		
		Order order = null;
		
		if(userPayment != null && cantinaCart.getGrandTotal().intValue() != 0) {
			 order = orderService.createOrder(cantinaCart, userPayment, user);
		}
	
		//mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));
		
		cantinaCartService.clearCantinaCart(cantinaCart);
	
		return order;
	}

	
	@PostMapping("/setPaymentMethod")
	public Payment setPaymentMethod(@RequestParam("userPaymentId") Long userPaymentId, Principal principal) throws Exception {

		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.findById(userPaymentId);

		if (userPayment.getUser().getId() != user.getId()) {
			throw new Exception("This is not your payment card");
		} else { 
			Payment newPayment = paymentService.setByUserPayment(userPayment, payment);
			
			
			return newPayment;
		}
	}
	

}
