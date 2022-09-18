package com.washapp.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.washapp.back.model.Customer;
import com.washapp.back.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> listCustomers() {
		return customerService.getAllCustomers();
	}
	
	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	@PostMapping("/customers/auth")
	public ResponseEntity<Customer> authCustomer(@RequestBody Customer customer) {
		return customerService.authCustomer(customer);
	}
	
	@GetMapping("/customers/id/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
		return customerService.updateCustomer(id, customer);
	}
	
}
