package com.washapp.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.washapp.back.exception.ResourceNotFoundException;
import com.washapp.back.model.Customer;
import com.washapp.back.repository.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		return customerRepository.save(customer);
	}

	@Override
	public ResponseEntity<Customer> authCustomer(Customer customer) {
		Customer customerResponse = findCustomerByEmail(customer.getEmail());
		boolean matches = passwordEncoder.matches(customer.getPassword(), customerResponse.getPassword());
		return matches ? ResponseEntity.ok(customerResponse) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@Override
	public ResponseEntity<Customer> getCustomerById(Long id) {
		Customer customer = findCustomerById(id);
		return ResponseEntity.ok(customer);
	}

	@Override
	public ResponseEntity<Customer> updateCustomer(Long id, Customer customer) {
		Customer customerToUpdate = findCustomerById(id);
		
		customerToUpdate.setEmail(customer.getEmail());
		customerToUpdate.setPhone(customer.getPhone());
		customerToUpdate.setCity(customer.getCity());
		customerToUpdate.setAddress(customer.getAddress());
		customerToUpdate.setCp(customer.getCp());
		
		boolean matches = passwordEncoder.matches(customer.getPassword(), customerToUpdate.getPassword());
		if (!matches) customerToUpdate.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		customerRepository.save(customerToUpdate);
		return ResponseEntity.ok(customerToUpdate);
	}

	private Customer findCustomerById(Long id) {
		return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: " + id));		
	}
	
	private Customer findCustomerByEmail(String email) {
		return customerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Customer not exist wrong email: " + email));
	}
}
