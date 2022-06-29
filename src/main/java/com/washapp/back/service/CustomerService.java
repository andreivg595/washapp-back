package com.washapp.back.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.washapp.back.model.Customer;

@Service
public interface CustomerService {

	List<Customer> getAllCustomers();
	
	Customer createCustomer(Customer customer);
	
	ResponseEntity<Customer> getCustomerById(Long id);
	
	ResponseEntity<Customer> getCustomerByEmail(String email);
	
	ResponseEntity<Customer> updateCustomer(Long id, Customer customer);
}
