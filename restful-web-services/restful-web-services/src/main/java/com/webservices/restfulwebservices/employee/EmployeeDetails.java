package com.webservices.restfulwebservices.employee;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeDetails {
	@Size(min = 2, message = "Name should contain atleast 2 character")
	private String name;
	private String designation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Past(message = "Date of birth should not be ")
	private LocalDate dateOfBirth;
	@Pattern(regexp = "(^$|[0-9]{10})", message = "phone number length should be 10")
	private String phoneNumber;
	@Email
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(@Past(message = "Date of birth should not be ") LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
