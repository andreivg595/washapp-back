package com.washapp.back.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.washapp.back.model.Employee;

@Service
public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	Employee createEmployee(Employee employee);
	
	ResponseEntity<Employee> authEmployee(Employee employee);
	
	ResponseEntity<Employee> getEmployeeById(Long id);
	
	ResponseEntity<Employee> updateEmployee(Long id, Employee employee);
	
	ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id);
}
