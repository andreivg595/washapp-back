package com.washapp.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.washapp.back.exception.ResourceNotFoundException;
import com.washapp.back.model.Cart;
import com.washapp.back.model.Customer;
import com.washapp.back.repository.CartRepository;

@Component
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public ResponseEntity<Cart> createCart(Customer customer) {
		
		Cart cart = new Cart();
		cart.setCustomer(customer);
		
		cartRepository.save(cart);
		return ResponseEntity.ok(cart);
	}

	@Override
	public ResponseEntity<Cart> getCartByCustomerId(Long customerId) {
		
		Cart cart = cartRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not exist with customer id : " + customerId));

		return ResponseEntity.ok(cart);
	}

}
