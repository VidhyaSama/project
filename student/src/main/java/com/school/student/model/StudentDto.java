package com.school.student.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDto {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer id;
	@Size(min = 2, message = "Name should contain atleast 2 character")
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Past(message = "Date of birth should not be ")
	private LocalDate dateOfBirth;
	@NotNull
	@Pattern(regexp = "(^$|[0-9]{10})", message = "phone number length should be 10")
	private String phoneNumber;
	private Integer courseId;
	public StudentDto(Integer id, String name, LocalDate dateOfBirth, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;

		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;

	}

	public StudentDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", phoneNumber="
				+ phoneNumber + "]";
	}

}
