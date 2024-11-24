package com.medical.patientService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.patientService.entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {

}
