package com.washapp.back.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.washapp.back.exception.ResourceNotFoundException;
import com.washapp.back.model.Employee;
import com.washapp.back.repository.EmployeeRepository;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Employee> getAllEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public Employee createEmployee(Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		return employeeRepository.save(employee);
	}
	
	@Override
	public ResponseEntity<Employee> authEmployee(Employee employee) {
		Employee employeeResponse = findEmployeeByEmail(employee.getEmail());
		boolean matches = passwordEncoder.matches(employee.getPassword(), employeeResponse.getPassword());
		return matches ? ResponseEntity.ok(employeeResponse) : ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Employee> getEmployeeById(Long id) {
		Employee employee = findEmployeeById(id);
		return ResponseEntity.ok(employee);
	}

	@Override
	public ResponseEntity<Employee> updateEmployee(Long id, Employee employee) {
		Employee employeeToUpdate = findEmployeeById(id);
		
		employeeToUpdate.setName(employee.getName());
		employeeToUpdate.setSurname(employee.getSurname());
		employeeToUpdate.setUsername(employee.getUsername());
		employeeToUpdate.setEmail(employee.getEmail());
		
		boolean matches = passwordEncoder.matches(employee.getPassword(), employeeToUpdate.getPassword());
		if (!matches) employeeToUpdate.setPassword(passwordEncoder.encode(employee.getPassword()));
		
		employeeRepository.save(employeeToUpdate);
		return ResponseEntity.ok(employeeToUpdate);
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) {
		Employee employee = findEmployeeById(id);
		employeeRepository.delete(employee);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	private Employee findEmployeeById(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));		
	}
	
	private Employee findEmployeeByEmail(String email) {
		return employeeRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Customer not exist wrong email : " + email));
	}
}
