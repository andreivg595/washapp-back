package com.washapp.back.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.washapp.back.exception.ResourceNotFoundException;
import com.washapp.back.model.Product;
import com.washapp.back.repository.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts(Integer type) {

		List<Product> products;

		if (type != null) {
			products = (List<Product>) productRepository.findByType(type);
		} else {
			products = (List<Product>) productRepository.findAll();
		}

		for (Product product : products) {
			product.setImage(decompressBytes(product.getImage()));
		}

		return products;
	}

	@Override
	public Product createProduct(Product product, MultipartFile file) {

		try {
			System.out.println("Original Image Byte Size - " + file.getBytes().length);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			product.setImage(compressBytes(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return productRepository.save(product);
	}

	@Override
	public ResponseEntity<Product> getProductById(Long id) {

		Product product = findEmployeeById(id);

		product.setImage(decompressBytes(product.getImage()));

		return ResponseEntity.ok(product);
	}

	@Override
	public ResponseEntity<Product> updateProduct(Long id, Product product, MultipartFile file) {

		Product productToUpdate = findEmployeeById(id);

		productToUpdate.setName(product.getName());
		productToUpdate.setPrice(product.getPrice());
		productToUpdate.setType(product.getType());

		try {
			if (file != null) {
				productToUpdate.setImage(compressBytes(file.getBytes()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		productRepository.save(productToUpdate);

		return ResponseEntity.ok(productToUpdate);
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deleteProduct(Long id) {

		Product product = findEmployeeById(id);

		productRepository.delete(product);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}

	private Product findEmployeeById(Long id) {

		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id : " + id));
	}

	// compress the image bytes before storing it in the database
	private static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	private static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
