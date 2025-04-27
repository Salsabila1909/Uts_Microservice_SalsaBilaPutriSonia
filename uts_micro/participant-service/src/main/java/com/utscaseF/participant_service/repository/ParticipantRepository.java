package com.utscaseF.participant_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utscaseF.participant_service.model.Participant;


@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
