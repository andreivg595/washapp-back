package com.washapp.back.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.washapp.back.model.Cart;
import com.washapp.back.model.Customer;

@Service
public interface CartService {

	ResponseEntity<Cart> createCart(Customer customer);
	
	ResponseEntity<Cart> getCartByCustomerId(Long customerId);
}
