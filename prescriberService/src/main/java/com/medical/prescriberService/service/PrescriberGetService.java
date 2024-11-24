package com.medical.prescriberService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.prescriberService.dto.AddressRespDTO;
import com.medical.prescriberService.dto.PrescriberRespDto;
import com.medical.prescriberService.entity.AddressEntity;
import com.medical.prescriberService.entity.PrescriberEntity;
import com.medical.prescriberService.repo.AddressRepo;
import com.medical.prescriberService.repo.PrescriberRepo;

@Service
public class PrescriberGetService {
	
	@Autowired
	PrescriberRepo presRepo;
	
	@Autowired
	AddressRepo addRepo;

	public PrescriberRespDto getPrescriber(int id) {
		PrescriberEntity ent= presRepo.findById(id).get();
		
		return PrescriberRespDto.builder()
					.id(ent.getId())
					.dob(ent.getDob())
					.gender(ent.getGender())
					.isActive(ent.getIsActive())
					.name(ent.getName())
					.build();
	}
	
	public List<AddressRespDTO> getActiveAdd(int presId) {
		List<AddressEntity> ent=addRepo.findActiveByPresId(presId).get();

		return ent.stream()
					.map(data->
						AddressRespDTO.builder()
							.addId(data.getId())
							.addLine1(data.getAddLine1())
							.addLine2(data.getAddLine2())
							.city(data.getCity())
							.state(data.getState())
							.zipCode(data.getZipCode())
							.isActive(data.getIsActive())
							.prescriberId(data.getPrescriberId())
							.build()
						)
					.toList();

	}
	
	public List<AddressRespDTO> getAllAdd(int presId) {
		List<AddressEntity> ent=addRepo.findByPrescriberId(presId).get();
		
		return ent.stream()
					.map(data->
						AddressRespDTO.builder()
							.addId(data.getId())
							.addLine1(data.getAddLine1())
							.addLine2(data.getAddLine2())
							.city(data.getCity())
							.state(data.getState())
							.zipCode(data.getZipCode())
							.isActive(data.getIsActive())
							.prescriberId(data.getPrescriberId())
							.build()
						)
					.toList();
	}
}
