package com.webservices.restfulwebservices.employee;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDto {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer id;
	@Size(min = 2, message = "Name should contain atleast 2 character")
	private String name;
	@NotNull(message = "Designation is mandatory")
	private String designation;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Past(message = "Date of birth should not be ")
	private LocalDate dateOfBirth;
	@NotNull
	@Pattern(regexp = "(^$|[0-9]{10})", message = "phone number length should be 10")
	private String phoneNumber;
	@Email
	private String email;
	@CreationTimestamp
	  @Temporal(TemporalType.TIMESTAMP)
	  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	  private Calendar createdAt;
	  
	  @UpdateTimestamp
	  @Temporal(TemporalType.TIMESTAMP)
	  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	  private Calendar updatedAt;
	

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	public EmployeeDto(Integer id, String name, String designation, LocalDate dateOfBirth, String phoneNumber,
			String email) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.email = email;

	}

	public EmployeeDto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", name=" + name + ", designation=" + designation + ", dateOfBirth="
				+ dateOfBirth + ", phoneNumber=" + phoneNumber + ", email=" + email + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
