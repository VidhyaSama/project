package com.webservices.restfulwebservices.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.webservices.restfulwebservices.employee.Employee;
import com.webservices.restfulwebservices.employee.EmployeeDetails;
import com.webservices.restfulwebservices.employee.EmployeeDto;
import com.webservices.restfulwebservices.employee.exception.EmployeeAvailableException;
import com.webservices.restfulwebservices.employee.exception.EmployeeNotFoundException;
import com.webservices.restfulwebservices.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		if (employees.isEmpty())
			throw new EmployeeNotFoundException("No Employees found");
		List<EmployeeDto> employeeList = employees.stream().map(employ -> {
			EmployeeDto emp = new EmployeeDto();
			BeanUtils.copyProperties(employ, emp);
			return emp;
		}).collect(Collectors.toList());

		return employeeList;
	}

	public EmployeeDto getEmployeeByID(Integer id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (!employee.isPresent())
			return null;
		EmployeeDto employeeDto = new EmployeeDto();
		BeanUtils.copyProperties(employee.get(), employeeDto);
		System.out.println(employeeDto.getId());
		return employeeDto;
	}

	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Optional<Employee> employee = employeeRepository.findByNameAndEmail(employeeDto.getName(),
				employeeDto.getEmail());
		if (employee.isPresent())
			throw new EmployeeAvailableException("Employee " + employeeDto.getEmail() + " is already registered");
		Employee emp_one = new Employee();
		BeanUtils.copyProperties(employeeDto, emp_one);
		Employee emp = employeeRepository.save(emp_one);
		BeanUtils.copyProperties(emp, employeeDto);
		return employeeDto;
	}

	public EmployeeDto deleteEmployee(String id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (!employee.isPresent())
			return null;
		EmployeeDto employeeDto = new EmployeeDto();
		BeanUtils.copyProperties(employee.get(), employeeDto);
		employeeRepository.deleteById(id);
		return employeeDto;
	}

	public EmployeeDto updateEmployee(EmployeeDetails employeeDto, String id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (!employee.isPresent())
			return null;
		Employee employ = new Employee();
		EmployeeDto empDto = new EmployeeDto();
		BeanUtils.copyProperties(employee.get(), employ);
		System.out.println(employee.get() + "hai");
		Employee emp = validateProperties(employeeDto, employ);
		System.out.println(emp);
		BeanUtils.copyProperties(emp, empDto);
		employeeRepository.save(emp);
		return empDto;
	}

	public Employee validateProperties(EmployeeDetails employeeDto, Employee emp) {
		if (employeeDto.getDesignation() != null)
			emp.setDesignation(employeeDto.getDesignation());
		if (employeeDto.getEmail() != null)
			emp.setEmail(employeeDto.getEmail());
		if (employeeDto.getName() != null)
			emp.setName(employeeDto.getName());
		if (employeeDto.getPhoneNumber() != null)
			emp.setPhoneNumber(employeeDto.getPhoneNumber());
		if (employeeDto.getDateOfBirth() != null)
			emp.setDateOfBirth(employeeDto.getDateOfBirth());
		System.out.println(emp);
		return emp;
	}

}
