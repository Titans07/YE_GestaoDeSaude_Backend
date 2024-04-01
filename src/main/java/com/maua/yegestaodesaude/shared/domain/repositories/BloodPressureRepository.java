package com.maua.yegestaodesaude.shared.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maua.yegestaodesaude.shared.domain.entities.BloodPressure;

@Repository
public interface BloodPressureRepository extends JpaRepository<BloodPressure, Long>{
}
