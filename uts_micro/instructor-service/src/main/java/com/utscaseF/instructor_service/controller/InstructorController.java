package com.utscaseF.instructor_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utscaseF.instructor_service.model.Instructor;
import com.utscaseF.instructor_service.service.InstructorService; // <- sudah benar!

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    private static final Logger log = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    private InstructorService instructorService; // <- sudah benar!

    // Endpoint untuk mengambil semua instructor
    @GetMapping
    public List<Instructor> getAllInstructors() {
        log.info("GET /api/instructor accessed");
        return instructorService.getAllInstructors();
    }

    // Endpoint untuk mengambil instructor berdasarkan id
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        log.info("GET /api/instructor/{} accessed", id);
        return instructorService.getInstructorById(id)
                .map(instructor -> ResponseEntity.ok().body(instructor))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint untuk membuat instructor baru
    @PostMapping
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        log.info("POST /api/instructor accessed with body: {}", instructor);
        return instructorService.createInstructor(instructor);
    }

    // Endpoint untuk mengupdate instructor yang sudah ada
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructorDetails) {
        log.info("PUT /api/instructor/{} accessed with body: {}", id, instructorDetails);
        try {
            Instructor updatedInstructor = instructorService.updateInstructor(id, instructorDetails);
            return ResponseEntity.ok(updatedInstructor);
        } catch (RuntimeException e) {
            log.warn("PUT /api/instructor/{} failed: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint untuk menghapus instructor
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteInstructor(@PathVariable Long id) {
        log.info("DELETE /api/instructor/{} accessed", id);
        Map<String, String> response = new HashMap<>();
        try {
            instructorService.deleteInstructor(id);
            response.put("message", "Instructor berhasil dihapus");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Instructor tidak ditemukan dengan id " + id);
            log.warn("DELETE /api/instructor/{} failed: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
