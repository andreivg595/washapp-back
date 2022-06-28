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
	
	ResponseEntity<Employee> getEmployeeById(Long id);
	
	ResponseEntity<Employee> getEmployeeByEmail(String email);
	
	ResponseEntity<Employee> updateEmployee(Long id, Employee employeeDetails);
	
	ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id);
}
