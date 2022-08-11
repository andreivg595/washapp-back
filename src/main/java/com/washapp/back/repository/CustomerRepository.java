package com.washapp.back.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.washapp.back.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	@Transactional(readOnly = true)
	Optional<Customer> findByEmail(String email);
}
