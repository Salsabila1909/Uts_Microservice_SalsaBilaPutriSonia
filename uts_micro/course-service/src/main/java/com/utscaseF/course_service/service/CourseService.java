package com.utscaseF.course_service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utscaseF.course_service.model.Course;
import com.utscaseF.course_service.repository.CourseRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CourseService {


   @Autowired
   private CourseRepository courseRepository;


   public List<Course> getAllCourse() {
       return courseRepository.findAll();
   }


   public Optional<Course> getCourseById(Long id) {
       return courseRepository.findById(id);
   }


   public Course createCourse(Course course) {
       return courseRepository.save(course);
   }


   public Course updateCourse(Long id, Course courseDetails) {
       Course course = courseRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Course tidak ditemukan dengan id " + id));
       course.setNama(courseDetails.getNama());
       course.setDeskripsi(courseDetails.getDeskripsi());
       course.setPengajar(courseDetails.getPengajar());
       course.setKategori(courseDetails.getKategori());
       return courseRepository.save(course);
   }


   public void deleteCourse(Long id) {
       Course course = courseRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Course tidak ditemukan dengan id " + id));
       courseRepository.delete(course);
   }
}

