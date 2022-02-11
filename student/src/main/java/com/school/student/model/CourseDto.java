package com.school.student.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseDto {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer id;
	private String courseCode;
	private String courseDescription;
	private BigDecimal price;

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	@Override
	public String toString() {
		return "CourseDto [id=" + id + ", courseCode=" + courseCode + ", courseDescription=" + courseDescription
				+ ", price=" + price + "]";
	}

	public CourseDto(Integer id, String courseCode, String courseDescription, BigDecimal price) {
		super();
		this.id = id;
		this.courseCode = courseCode;
		this.courseDescription = courseDescription;
		this.price = price;
	}

	public CourseDto() {
		// TODO Auto-generated constructor stub
	}

}
