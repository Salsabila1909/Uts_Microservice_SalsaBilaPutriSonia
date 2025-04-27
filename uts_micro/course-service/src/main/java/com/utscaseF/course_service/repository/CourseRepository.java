package com.utscaseF.course_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utscaseF.course_service.model.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
