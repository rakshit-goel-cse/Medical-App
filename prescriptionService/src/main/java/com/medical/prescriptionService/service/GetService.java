package com.medical.prescriptionService.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.medical.prescriptionService.dto.DrugDTO;
import com.medical.prescriptionService.dto.PrescriberDto;
import com.medical.prescriptionService.dto.PrescriptionResponseDto;
import com.medical.prescriptionService.entity.PrescriptionEntity;
import com.medical.prescriptionService.repo.PrescriptionRepo;

@Service
public class GetService {

	@Autowired
	PrescriptionRepo repo;
	
	@Autowired
	RestTemplate restTemp;
	
	//Get drug data
	private DrugDTO getdrugDto(int id) {
		DrugDTO drug=null;
		if(id>0) {
		drug = restTemp.getForObject("http://drugService/drug/getDrugById/"+id, DrugDTO.class);
		}
		return drug;
	}
	
	//get prescriber info
	private PrescriberDto getPrescriberDto(int id) {
		Map<String, Integer> uriVariables = new HashMap<String, Integer>();
		
		PrescriberDto prescriber=null;
		if(id>0) {
			uriVariables.put("id", id);
			prescriber= restTemp.getForObject("http://prescriberService/prescriber/{id}", PrescriberDto.class,uriVariables);
		}
		return prescriber;
	}
	
	public List<PrescriptionResponseDto> getPrescByPatId(int patId) {
		List<PrescriptionEntity> entList= repo.findByPatId(patId).get();
		
		List<PrescriptionResponseDto> dto = 
				entList.stream()
					.map(ent->
					PrescriptionResponseDto.builder()
					.id(ent.getId())
					.patId(ent.getPatId())
					.pickupDate(ent.getPickupDate())
					.creationDate(ent.getCreationDate())
					.storeNum(ent.getStoreNumber())
					.prescriber(getPrescriberDto(ent.getPrescriberId()))
					.drug(getdrugDto(ent.getDrugId()))
					.build()
					)
				.toList();
		
		return dto;
	}
	
	public Page<PrescriptionResponseDto> getPrescPagerByPatId(int patId,int offset, int pageSize) {
		Page<PrescriptionEntity> entList= repo.findByPatId(PageRequest.of(offset, pageSize),patId);
		System.out.println("/n----------------------/n\n data fetched-"+entList.getSize());
		Page<PrescriptionResponseDto> dto = 
				entList
					.map(ent->
							
									PrescriptionResponseDto.builder()
									.id(ent.getId())
									.patId(ent.getPatId())
									.pickupDate(ent.getPickupDate())
									.creationDate(ent.getCreationDate())
									.storeNum(ent.getStoreNumber())
									.prescriber(getPrescriberDto(ent.getPrescriberId()))
									.drug(getdrugDto(ent.getDrugId()))
									.build()
					);
		
		return dto;
	}
	
}
