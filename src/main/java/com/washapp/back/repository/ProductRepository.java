package com.washapp.back.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.washapp.back.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
