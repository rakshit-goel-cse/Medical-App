package com.medical.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.userService.entity.patient.PatientAddressEntity;

public interface PatientAddressRepository extends JpaRepository<PatientAddressEntity, Integer> {

}
