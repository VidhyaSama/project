package com.course.location.controller;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.course.location.exception.CourseNotFoundException;
import com.course.location.model.Course;
import com.course.location.model.CourseDto;

import com.course.location.service.CourseService;


@RestController
public class CourseController {
	@Autowired
	private CourseService studentService;

	@GetMapping("/courses")
	public List<CourseDto> getcourses() {
		return studentService.getAllcourses();
	}
//	@GetMapping("/courses")
//	public List<StudentDto> getCourses() {
//		return studentService.getAllCourses();
//	}


//	@GetMapping("/employees/{id}")
//	public EntityModel<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
//		EmployeeDto employee = studentService.getEmployeeByID(id);
//		if (employee == null) {
//			throw new EmployeeNotFoundException("Employee " + id + " not found");
//		}
//		EntityModel<EmployeeDto> model = EntityModel.of(employee);
//		WebMvcLinkBuilder linkToEmployees = linkTo(methodOn(this.getClass()).getEmployees());
//		model.add(linkToEmployees.withRel("all-users"));
//		return model;
//	}

	/*
	 * Status :created URI
	 */

	@PostMapping("/course")
	public ResponseEntity<Course> saveCourse(@Valid @RequestBody CourseDto courseDto) {
		CourseDto course = studentService.saveDetails(courseDto);
		URI location = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8080/employees").path("/{id}")
				.buildAndExpand(course.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
//	@DeleteMapping("/courses/{courseCode}")
//	public CourseDto  deleteById(@PathVariable String  courseCode) {
//		CourseDto course = studentService.deleteCourse(courseCode);
//		if (course== null) {
//			throw new CourseNotFoundException("couse" +courseCode + " not found");
//		}
//		return course;
//	}
	@PutMapping("/update/{courseCode}")
	public  CourseDto saveEmployee( @RequestBody CourseDto courseDto,@PathVariable String courseCode) {
		 CourseDto course= studentService.updateCourse(courseDto,courseCode);
		return course;
	}

}
