package com.cantina.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cantina.model.CantinaCart;
import com.cantina.model.CartItem;
import com.cantina.model.FoodProduct;
import com.cantina.model.User;
import com.cantina.service.CantinaCartService;
import com.cantina.service.CartItemService;
import com.cantina.service.FoodProductService;
import com.cantina.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/api/cantinaCart")  
public class CantinaCartController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FoodProductService foodProductService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private CantinaCartService cantinaCartService;
	
	@GetMapping("/cart")
	public List<CartItem> cantinaCart(Principal principal) {
		
		User user = userService.findByUsername(principal.getName());
		CantinaCart cantinaCart = user.getCantinaCart();
		
		List<CartItem> cartItemList = cartItemService.findByCantinaCart(cantinaCart);
		
		cantinaCartService.updateCantinaCart(cantinaCart);
		
		return cartItemList;
	}
	
	@PostMapping("/addProductToCartItem/{id}")
	public CartItem addProductToCartItem(@PathVariable("id") Long id, Principal principal) throws Exception {
		
		User user = userService.findByUsername(principal.getName());
		FoodProduct foodProduct = foodProductService.findById(id);
		
		CartItem cartItem = cartItemService.addProductToCartItem(foodProduct, user);
		
		return cartItem;
	}
	
	@DeleteMapping("/deleteProductFromCart/{id}")
	public void deleteItemFromCart(@PathVariable("id") Long id) {
		 cartItemService.deleteItemFromCart(cartItemService.findById(id));
	}
	
	@PostMapping("/updateCartItem")
	public CartItem updateCantinaCart(@RequestParam("qty") String qty, @RequestParam("id") Long id) throws NumberFormatException, Exception {

		CartItem cartItem = cartItemService.findById(id);
		if(cartItem == null) {
			throw new Exception("This cart item does not exist!");
		} else {
			System.out.println(Integer.parseInt(qty));
			cartItem = cartItemService.updateCartItem(cartItem, Integer.parseInt(qty));
			return cartItem;
		}
	}

}
