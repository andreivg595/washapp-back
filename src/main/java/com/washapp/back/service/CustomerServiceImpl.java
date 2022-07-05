package com.washapp.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.washapp.back.exception.ResourceNotFoundException;
import com.washapp.back.model.Customer;
import com.washapp.back.repository.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public ResponseEntity<Customer> authCustomer(Customer customer) {
		
		Customer customerResponse = customerRepository.findByEmailAndPassword(customer.getEmail(), customer.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Customer not exist wrong email or password"));
		
		return ResponseEntity.ok(customerResponse);
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
		customerToUpdate.setPassword(customer.getPassword());
		customerToUpdate.setPhone(customer.getPhone());
		customerToUpdate.setCity(customer.getCity());
		customerToUpdate.setAddress(customer.getAddress());
		customerToUpdate.setCp(customer.getCp());
		
		customerRepository.save(customerToUpdate);
		
		return ResponseEntity.ok(customerToUpdate);
	}

	private Customer findCustomerById(Long id) {
		
		return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id : " + id));		
	}
}
