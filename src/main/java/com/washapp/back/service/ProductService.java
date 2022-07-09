package com.washapp.back.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.washapp.back.model.Product;

@Service
public interface ProductService {

	List<Product> getAllProducts();
	
	Product createProduct(Product product, MultipartFile file);
	
	ResponseEntity<Product> getProductById(Long id);
	
	ResponseEntity<Product> updateProduct(Long id, Product product, MultipartFile file);
	
	ResponseEntity<Map<String, Boolean>> deleteProduct(Long id);
}
