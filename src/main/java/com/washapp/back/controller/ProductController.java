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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.washapp.back.model.Product;
import com.washapp.back.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<Product> listEmployees() {
		return productService.getAllProducts();
	}

	@PostMapping("/products")
	public Product createEmployee(@RequestPart("body") Product product, @RequestPart("imageFile") MultipartFile file) {
		return productService.createProduct(product, file);
	}

	// get product by id
	@GetMapping("products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestPart("body") Product product,
			@RequestPart("imageFile") MultipartFile file) {
		return productService.updateProduct(id, product, file);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
}
