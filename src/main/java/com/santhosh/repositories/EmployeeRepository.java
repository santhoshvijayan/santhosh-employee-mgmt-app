package com.santhosh.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.santhosh.models.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
	
}