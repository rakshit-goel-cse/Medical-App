package com.medical.prescriptionService.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.medical.prescriptionService.dto.DrugDTO;
import com.medical.prescriptionService.dto.PrescriberDto;
import com.medical.prescriptionService.dto.PrescriptionDto;
import com.medical.prescriptionService.entity.PrescriptionEntity;
import com.medical.prescriptionService.repo.PrescriptionRepo;

@Service
public class GetService {

	@Autowired
	PrescriptionRepo repo;
	
	@Autowired
	RestTemplate restTemp;
	
	public PrescriptionDto getPrescByPatId(int patId) {
		PrescriptionEntity ent= repo.findByPatId(patId).get();
		
		Map<String, Integer> uriVariables = new HashMap();
		
		PrescriberDto prescriber=null;
		if(null!=ent && ent.getPrescriberId()>0) {
			uriVariables.put("id", ent.getPrescriberId());
			prescriber= restTemp.getForObject("http://localhost:8082/prescriber/{id}", PrescriberDto.class,uriVariables);
		}
		
		DrugDTO drug=null;
		if(null!=ent && ent.getPrescriberId()>0) {
		drug = restTemp.getForObject("http://localhost:8084/drug/getDrugById/"+ent.getDrugId(), DrugDTO.class);
		}
		PrescriptionDto dto = PrescriptionDto.builder()
									.id(ent.getId())
									.patId(ent.getPatId())
									.pickupDate(ent.getPickupDate())
									.creationDate(ent.getCreationDate())
									.storeNum(ent.getStoreNumber())
									.prescriber(prescriber)
									.drug(drug)
									.build();
		
		return dto;
	}
}
