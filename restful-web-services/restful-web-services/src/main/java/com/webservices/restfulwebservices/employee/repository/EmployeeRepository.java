package com.webservices.restfulwebservices.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webservices.restfulwebservices.employee.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String>{

	Optional<Employee> findByNameAndEmail(String name, String email);

	Optional<Employee> findById(Integer id);

}
