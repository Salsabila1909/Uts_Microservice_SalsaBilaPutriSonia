package com.utscaseF.instructor_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utscaseF.instructor_service.model.Instructor;
import com.utscaseF.instructor_service.repository.InstructorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor instructorDetails) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor tidak ditemukan dengan id " + id));

        instructor.setNama(instructorDetails.getNama());
        instructor.setEmail(instructorDetails.getEmail());
        instructor.setDepartment(instructorDetails.getDepartment());

        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor tidak ditemukan dengan id " + id));
        instructorRepository.delete(instructor);
    }
}
