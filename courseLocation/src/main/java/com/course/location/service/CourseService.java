package com.course.location.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.location.exception.CourseAvailableException;
import com.course.location.exception.CourseNotFoundException;
import com.course.location.model.Course;
import com.course.location.model.CourseDto;
import com.course.location.repository.CourseRepository;


@Service
public class CourseService {
	
	
	@Autowired
	private CourseRepository courseRepository;
	Course course_one=new Course();
	CourseDto courseDto_one=new CourseDto();
	

	public CourseDto saveDetails(CourseDto courseDto) {
		Course course=courseRepository.findByCourseCode(courseDto.getCourseCode());
		if(course!=null)
			throw new CourseAvailableException("Course with the "+courseDto.getCourseCode()+" already available");
		System.out.println(courseDto);
		Course courseDetails=new Course();
		BeanUtils.copyProperties(courseDto,courseDetails);
		System.out.println(courseDetails);
		 Course course_new=courseRepository.save(courseDetails);
		BeanUtils.copyProperties(course_new, courseDto);
		return courseDto;
		
	}

	public CourseDto updateCourse(CourseDto courseDto, String courseCode) {
		Course course = courseRepository.findByCourseCode(courseCode);
		if (course == null)
			throw new CourseNotFoundException("Course with the " + courseDto.getCourseCode() + " not available");
		course = validateProperties(courseDto, course);
		BeanUtils.copyProperties(course, courseDto_one);
		courseRepository.save(course);
		return courseDto_one;
	}

	public Course validateProperties(CourseDto courseDto, Course course) {
		if (courseDto.getCourseCode() != null)
			course.setCourseCode(courseDto.getCourseCode());
		if (courseDto.getCourseDescription() != null)
			course.setCourseDescription(courseDto.getCourseDescription());
		if (courseDto.getPrice() != null)
			course.setPrice(courseDto.getPrice());

		return course;
	}

	public List<CourseDto> getAllcourses() {
		List<Course> courses = courseRepository.findAll();
		if (courses.size()==0)
			throw new CourseNotFoundException("Courses are not available");
		List<CourseDto> courseList = courses.stream().map(course -> {
			CourseDto courseDto = new CourseDto();
			BeanUtils.copyProperties(course,courseDto);
			return courseDto;
		}).collect(Collectors.toList());
		
		return courseList;
	}
	
//	public CourseDto deleteCourse(String courseCode) {
//		Course course = courseRepository.findByCourseCode(courseCode);
//		if (course == null)
//			return null;
//		BeanUtils.copyProperties(course, courseDto_one);
//		courseRepository.deleteById(course.getId());
//		return courseDto_one;
//	}

	 

}
