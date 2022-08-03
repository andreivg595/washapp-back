package com.washapp.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.washapp.back.model.CartDetail;

@Repository
public interface CartDetailRepository extends CrudRepository<CartDetail, Long> {

	@Transactional(readOnly = true)
	List<CartDetail> findByCartId(Long cartId);
	
	@Transactional(readOnly = true)
	Optional<CartDetail> findByCartIdAndProductId(Long cartId, Long productId);
	
	@Transactional(readOnly = true)
	Long deleteByCartId(Long cartId);
}
