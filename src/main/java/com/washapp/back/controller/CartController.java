package com.washapp.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.washapp.back.model.Cart;
import com.washapp.back.model.Customer;
import com.washapp.back.service.CartService;

@RestController
@RequestMapping("/api/v1")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/cart")
	public ResponseEntity<Cart> createCart(@RequestBody Customer customer) {
		return cartService.createCart(customer);
	}
	
	@GetMapping("cart/{customerId}")
	public ResponseEntity<Cart> getCartByCustomerId(@PathVariable Long customerId) {
		return cartService.getCartByCustomerId(customerId);
	}
}
