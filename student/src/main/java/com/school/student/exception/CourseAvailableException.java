package com.school.student.exception;

@SuppressWarnings("serial")
public class CourseAvailableException extends RuntimeException {
	public CourseAvailableException(String message) {
		super(message);
		
	}
}
