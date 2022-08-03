package com.washapp.back.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.washapp.back.exception.ResourceNotFoundException;
import com.washapp.back.model.CartDetail;
import com.washapp.back.repository.CartDetailRepository;

@Component
public class CartDetailServiceImpl implements CartDetailService {

	@Autowired
	private CartDetailRepository cartDetailRepository;

	@Override
	public CartDetail createCartDetail(CartDetail cartDetail) {
		return cartDetailRepository.save(cartDetail);
	}

	@Override
	public List<CartDetail> getCartDetailProductsByCartId(Long cartId) {
		return cartDetailRepository.findByCartId(cartId);
	}

	@Override
	public ResponseEntity<CartDetail> getCartByCustomerIdAndProductId(Long cartId, Long productId) {

		CartDetail cartDetail = findCartDetailById(cartId, productId);

		return ResponseEntity.ok(cartDetail);
	}

	@Override
	public ResponseEntity<CartDetail> updateCartDetailProductQuantity(Long id, CartDetail cartDetail) {

		CartDetail cartDetailToUpdate = cartDetailRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cart detail not exist with id : " + id));

		cartDetailToUpdate.setQuantity(cartDetail.getQuantity());

		cartDetailRepository.save(cartDetailToUpdate);

		return ResponseEntity.ok(cartDetailToUpdate);
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deleteCartDetailProduct(Long cartId, Long productId) {

		CartDetail cartDetail = findCartDetailById(cartId, productId);
		
		cartDetailRepository.delete(cartDetail);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deleteAllCartDetailProducts(Long cartId) {
		
		Map<String, Boolean> response = new HashMap<>();
		
		Long deleted = cartDetailRepository.deleteByCartId(cartId);
		
		if (deleted > 0) {
			response.put("deleted", Boolean.TRUE);
		}
		
		return ResponseEntity.ok(response);
	}

	private CartDetail findCartDetailById(Long cartId, Long productId) {

		return cartDetailRepository.findByCartIdAndProductId(cartId, productId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Cart detail not exist with cartId: " + cartId + " and productId: " + productId));
	}
}
