package com.medical.prescriptionService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.prescriptionService.dto.PrescriptionRequestDto;
import com.medical.prescriptionService.entity.PrescriptionEntity;
import com.medical.prescriptionService.repo.PrescriptionRepo;

@Service
public class SetService {

	@Autowired
	PrescriptionRepo repo;
	
	public void newPrescription(PrescriptionRequestDto dto) {
		PrescriptionEntity ent=PrescriptionEntity.builder()
									.creationDate(dto.getCreationDate())
									.drugId(dto.getDrugId())
									.patId(dto.getPatId())
									.pickupDate(dto.getPickupDate())
									.prescriberId(dto.getPrescriberId())
									.storeNumber(dto.getStoreNum())
									.build();
		
		repo.save(ent);
	}
}
