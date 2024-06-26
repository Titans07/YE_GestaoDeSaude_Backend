package com.maua.yegestaodesaude.shared.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maua.yegestaodesaude.shared.domain.entities.Imc;

@Repository
public interface ImcRepository extends JpaRepository<Imc, Long> {
    List<Imc> findAllByClientId(Long clientId);
}
