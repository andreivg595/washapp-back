package com.washapp.back.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.washapp.back.model.CartDetail;
import com.washapp.back.service.CartDetailService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class CartDetailController {

	@Autowired
	private CartDetailService cartDetailService;
	
	@PostMapping("/cartdetail")
	public CartDetail createCartDetail(@RequestBody CartDetail cartDetail) {
		return cartDetailService.createCartDetail(cartDetail);
	}
	
	@GetMapping("/cartdetail/{cartId}")
	public List<CartDetail> getCartDetailProductsByCartId(@PathVariable Long cartId) {
		return cartDetailService.getCartDetailProductsByCartId(cartId);
	}
	
	@GetMapping("/cartdetail/{cartId}/{productId}")
	public ResponseEntity<CartDetail> getCartByCustomerIdAndProductId(@PathVariable Long cartId, @PathVariable Long productId) {
		return cartDetailService.getCartByCustomerIdAndProductId(cartId, productId);
	}
	
	@PutMapping("/cartdetail/{id}")
	public ResponseEntity<CartDetail> updateCartDetailProductQuantity(@PathVariable Long id, @RequestBody CartDetail cartDetail){
		return cartDetailService.updateCartDetailProductQuantity(id, cartDetail);
	}
	
	@DeleteMapping("/cartdetail/{cartId}/{productId}")
	public ResponseEntity<Map<String, Boolean>> deleteCartDetailProduct(@PathVariable Long cartId, @PathVariable Long productId){
		return cartDetailService.deleteCartDetailProduct(cartId, productId);
	}
	
	@DeleteMapping("/cartdetail/{cartId}")
	public ResponseEntity<Map<String, Boolean>> deleteAllCartDetailProducts(@PathVariable Long cartId){
		return cartDetailService.deleteAllCartDetailProducts(cartId);
	}
}
