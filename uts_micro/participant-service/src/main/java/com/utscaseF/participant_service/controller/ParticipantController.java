package com.utscaseF.participant_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utscaseF.participant_service.model.Participant;
import com.utscaseF.participant_service.service.ParticipantService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/participant")
public class ParticipantController {

    private static final Logger log = LoggerFactory.getLogger(ParticipantController.class);

    @Autowired
    private ParticipantService participantService;

    // Endpoint untuk mengambil semua participant
    @GetMapping
    public List<Participant> getAllParticipants() {
        log.info("GET /api/participant accessed");
        return participantService.getAllParticipant();
    }

    // Endpoint untuk mengambil participant berdasarkan id
    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        log.info("GET /api/participant/{} accessed", id);
        return participantService.getParticipantById(id)
                .map(participant -> ResponseEntity.ok().body(participant))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint untuk membuat participant baru
    @PostMapping
    public Participant createParticipant(@RequestBody Participant participant) {
        log.info("POST /api/participant accessed with body: {}", participant);
        return participantService.createParticipant(participant);
    }

    // Endpoint untuk mengupdate participant yang sudah ada
    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Participant participantDetails) {
        log.info("PUT /api/participant/{} accessed with body: {}", id, participantDetails);
        try {
            Participant updatedParticipant = participantService.updateParticipant(id, participantDetails);
            return ResponseEntity.ok(updatedParticipant);
        } catch (RuntimeException e) {
            log.warn("PUT /api/participant/{} failed: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint untuk menghapus participant
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteParticipant(@PathVariable Long id) {
        log.info("DELETE /api/participant/{} accessed", id);
        Map<String, String> response = new HashMap<>();
        try {
            participantService.deleteParticipant(id);
            response.put("message", "Participant berhasil dihapus");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Participant tidak ditemukan dengan id " + id);
            log.warn("DELETE /api/participant/{} failed: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
