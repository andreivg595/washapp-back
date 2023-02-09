package com.washapp.back.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.washapp.back.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	@Transactional(readOnly = true)
	List<Product> findByType(int type);
}
