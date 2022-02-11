package com.webservices.restfulwebservices.employee.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservices.restfulwebservices.employee.Employee;
import com.webservices.restfulwebservices.employee.EmployeeDetails;
import com.webservices.restfulwebservices.employee.EmployeeDto;
import com.webservices.restfulwebservices.employee.exception.EmployeeNotFoundException;
import com.webservices.restfulwebservices.employee.service.EmployeeService;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<EmployeeDto> getEmployees() {
		return employeeService.getAllEmployees();
	}


	@GetMapping("/employees/{id}")
	public EntityModel<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
		EmployeeDto employee = employeeService.getEmployeeByID(id);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee " + id + " not found");
		}
		EntityModel<EmployeeDto> model = EntityModel.of(employee);
		WebMvcLinkBuilder linkToEmployees = linkTo(methodOn(this.getClass()).getEmployees());
		model.add(linkToEmployees.withRel("all-users"));
		return model;
	}

	/*
	 * Status :created URI
	 */

	@PostMapping("/employee")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeDto employee) {
		EmployeeDto emp = employeeService.saveEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8080/employees").path("/{id}")
				.buildAndExpand(emp.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/delete/{id}")
	public EmployeeDto deleteById(@PathVariable String id) {
		EmployeeDto employee = employeeService.deleteEmployee(id);
		if (employee == null) {
			throw new RuntimeException("Employee " + id + " not found");
		}
		return employee;
	}
	@PutMapping("/update/{id}")
	public EmployeeDto saveEmployee(@Valid @RequestBody EmployeeDetails employee,@PathVariable String id) {
		EmployeeDto emp = employeeService.updateEmployee(employee,id);
		return emp;
	}

}
