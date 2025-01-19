package com.medical.prescriptionService.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.google.common.base.Optional;
import com.medical.prescriptionService.entity.PrescriptionEntity;

public interface PrescriptionRepo extends JpaRepository<PrescriptionEntity, Integer> {

	
	public Optional<List<PrescriptionEntity>> findByPatId(int patId);

	public Page<PrescriptionEntity> findByPatId(PageRequest page,int id);
}
