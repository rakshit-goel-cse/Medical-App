package com.medical.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.userService.entity.patient.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {

}
