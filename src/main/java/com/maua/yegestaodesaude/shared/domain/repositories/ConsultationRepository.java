package com.maua.yegestaodesaude.shared.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maua.yegestaodesaude.shared.domain.entities.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findAllByClientId(Long clientId);
}
