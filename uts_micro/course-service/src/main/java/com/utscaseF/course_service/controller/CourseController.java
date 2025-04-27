package com.utscaseF.course_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utscaseF.course_service.model.Course;
import com.utscaseF.course_service.service.CourseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    // Endpoint untuk mengambil semua course
    @GetMapping
    public List<Course> getAllCourses() {
        log.info("GET /api/course accessed");
        return courseService.getAllCourse();
    }

    // Endpoint untuk mengambil course berdasarkan id
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        log.info("GET /api/course/{} accessed", id);
        return courseService.getCourseById(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint untuk membuat course baru
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        log.info("POST /api/course accessed with body: {}", course);
        return courseService.createCourse(course);
    }

    // Endpoint untuk mengupdate course yang sudah ada
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        log.info("PUT /api/course/{} accessed with body: {}", id, courseDetails);
        try {
            Course updatedCourse = courseService.updateCourse(id, courseDetails);
            return ResponseEntity.ok(updatedCourse);
        } catch (RuntimeException e) {
            log.warn("PUT /api/course/{} failed: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint untuk menghapus course
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCourse(@PathVariable Long id) {
        log.info("DELETE /api/course/{} accessed", id);
        Map<String, String> response = new HashMap<>();
        try {
            courseService.deleteCourse(id); // <- yang sebelumnya tidak dipanggil
            response.put("message", "Course berhasil dihapus");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Course tidak ditemukan dengan id " + id);
            log.warn("DELETE /api/course/{} failed: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
