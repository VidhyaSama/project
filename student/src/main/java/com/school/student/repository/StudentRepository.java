package com.school.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.student.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
