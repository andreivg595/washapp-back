package com.washapp.back.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.washapp.back.model.CartDetail;

@Service
public interface CartDetailService {

	CartDetail createCartDetail(CartDetail cartDetail);
	
	List<CartDetail> getCartDetailProductsByCartId(Long cartId);
	
	ResponseEntity<CartDetail> getCartByCustomerIdAndProductId(Long cartId, Long productId);
	
	ResponseEntity<CartDetail> updateCartDetailProductQuantity(Long id, CartDetail cartDetail);
	
	ResponseEntity<Map<String, Boolean>> deleteCartDetailProduct(Long cartId, Long productId);
	
	ResponseEntity<Map<String, Boolean>> deleteAllCartDetailProducts(Long cartId);
	
}
