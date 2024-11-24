package com.medical.prescriberService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.prescriberService.dto.PrescriberRespDto;
import com.medical.prescriberService.entity.AddressEntity;
import com.medical.prescriberService.entity.PrescriberEntity;
import com.medical.prescriberService.repo.AddressRepo;
import com.medical.prescriberService.repo.PrescriberRepo;

import jakarta.transaction.Transactional;

@Service
public class PrescriberSetService {

	@Autowired
	PrescriberRepo presRepo;
	
	@Autowired
	AddressRepo addRepo;
	
	@Transactional
	public boolean createNewPrescriber(PrescriberRespDto dto) {
		PrescriberEntity newPre= presRepo.save(
					PrescriberEntity.builder()
						.dob(dto.getDob())
						.gender(dto.getGender())
						.isActive("Y")
						.name(dto.getName())
						.type(dto.getType())
						.build()
				);
 
		//if address data present with prescriber info
		if(null!=dto.getAddress() && !dto.getAddress().isEmpty()) {
			addRepo.saveAll(
						dto.getAddress().stream()
							.map( data->
									AddressEntity.builder()
										.id(data.getId())
										.addLine1(data.getAddLine1())
										.addLine2(data.getAddLine2())
										.city(data.getCity())
										.isActive("Y")
										.prescriberId(newPre.getId())
										.zipCode(data.getZipCode())
										.state(data.getState())
										.build()
						)
							.toList()
					);
		}
		
		return true;
	}
}
