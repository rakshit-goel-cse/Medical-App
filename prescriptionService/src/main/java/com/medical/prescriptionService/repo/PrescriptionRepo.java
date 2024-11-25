package com.medical.prescriptionService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.google.common.base.Optional;
import com.medical.prescriptionService.entity.PrescriptionEntity;

public interface PrescriptionRepo extends JpaRepository<PrescriptionEntity, Integer> {

	
	public Optional<PrescriptionEntity> findByPatId(int patId);
}
