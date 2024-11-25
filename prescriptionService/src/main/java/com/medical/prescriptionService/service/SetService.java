package com.medical.prescriptionService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.prescriptionService.dto.PrescriptionDto;
import com.medical.prescriptionService.entity.PrescriptionEntity;
import com.medical.prescriptionService.repo.PrescriptionRepo;

@Service
public class SetService {

	@Autowired
	PrescriptionRepo repo;
	
	public void newPrescription(PrescriptionDto dto) {
		PrescriptionEntity ent=PrescriptionEntity.builder()
									.creationDate(dto.getCreationDate())
									.drugId(1)
									.patId(dto.getPatId())
									.pickupDate(dto.getPickupDate())
									.prescriberId(1)
									.storeNumber(dto.getStoreNum())
									.build();
		
		repo.save(ent);
	}
}
