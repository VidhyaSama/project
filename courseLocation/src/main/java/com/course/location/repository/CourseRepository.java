package com.course.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.location.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByCourseCode(String courseCode);

	void deleteById(Integer id);

}
