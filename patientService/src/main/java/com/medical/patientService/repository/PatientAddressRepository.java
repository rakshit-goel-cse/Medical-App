package com.medical.patientService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.patientService.entity.PatientAddressEntity;

public interface PatientAddressRepository extends JpaRepository<PatientAddressEntity, Integer> {

}
