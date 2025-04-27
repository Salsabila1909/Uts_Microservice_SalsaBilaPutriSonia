package com.utscaseF.instructor_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utscaseF.instructor_service.model.Instructor;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
