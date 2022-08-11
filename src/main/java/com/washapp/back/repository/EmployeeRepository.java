package com.washapp.back.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.washapp.back.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	@Transactional(readOnly = true)
	Optional<Employee> findByEmail(String email);
}
