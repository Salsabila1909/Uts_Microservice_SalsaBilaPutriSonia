package com.utscaseF.participant_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utscaseF.participant_service.model.Participant;
import com.utscaseF.participant_service.repository.ParticipantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public List<Participant> getAllParticipant() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant updateParticipant(Long id, Participant participantDetails) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant tidak ditemukan dengan id " + id));

        participant.setNama(participantDetails.getNama());
        participant.setEmail(participantDetails.getEmail());
        participant.setNohp(participantDetails.getNohp());
        participant.setRegistrasi_date(participantDetails.getRegistrasi_date());

        return participantRepository.save(participant);
    }

    public void deleteParticipant(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant tidak ditemukan dengan id " + id));
        participantRepository.delete(participant);
    }
}
