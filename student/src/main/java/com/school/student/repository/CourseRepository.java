package com.school.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.student.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByCourseCode(String courseCode);

	void deleteById(Integer id);

}
