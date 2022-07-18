package com.washapp.back.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.washapp.back.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

	@Transactional(readOnly = true)
	Optional<Cart> findByCustomerId(Long customerId);
}
