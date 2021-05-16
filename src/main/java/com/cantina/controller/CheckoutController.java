package com.cantina.controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cantina.model.CantinaCart;
import com.cantina.model.CartItem;
import com.cantina.model.Order;
import com.cantina.model.User;
import com.cantina.model.UserPayment;
import com.cantina.model.dao.CartItemDao;
import com.cantina.model.dao.CheckoutDao;
import com.cantina.model.dao.OrderDao;
import com.cantina.service.CantinaCartService;
import com.cantina.service.CartItemService;
import com.cantina.service.OrderService;
import com.cantina.service.UserService;
import com.cantina.utility.MailConstructor;

@RestController
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping("/api/checkout")
public class CheckoutController {
	
	@Autowired
    private KafkaTemplate<String, OrderDao> template;

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;

	
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
	public OrderDao checkoutPost(Principal principal) throws Exception {
		
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
		OrderDao orderDao = null;
		
		if(existsUserPayment(userPayment) && notEmptyCart(cantinaCart)) {
			order = orderService.createOrder(cantinaCart, userPayment, user);
			List<CartItem> cartItemList = order.getCartItemList();
			List<CartItemDao> cartItemDaoList = getProducts(cartItemList);
			
			orderDao = new OrderDao(user.getUsername(), cantinaCart.getGrandTotal(), cartItemDaoList);
			template.send("TestKafka", orderDao);
		}
	
		mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order));
		
		cantinaCartService.clearCantinaCart(cantinaCart);
	
		return orderDao;
	}
	
	private boolean existsUserPayment(UserPayment userPayment) {
		if(userPayment != null)
			return true;
		return false;
	}
	
	private boolean notEmptyCart(CantinaCart cantinaCart) {
		if(cantinaCart.getGrandTotal().intValue() != 0)
			return true;
		return false;
	}

	private List<CartItemDao> getProducts(List<CartItem> cartItemList){
		List<CartItemDao> cartItemDaoList = new ArrayList<CartItemDao>();
		for(CartItem cartItem : cartItemList) {
			CartItemDao cartItemDao = new CartItemDao(cartItem.getQty(), cartItem.getFoodProduct().getTitle());
			cartItemDaoList.add(cartItemDao);
		}
		
		return cartItemDaoList;
	}
}
