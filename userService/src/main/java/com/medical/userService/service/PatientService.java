package com.medical.userService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.medical.userService.dto.patient.PatientDTO;
import com.medical.userService.entity.patient.PatientEntity;
import com.medical.userService.repository.PatientRepository;


@Service
public class PatientService {

	@Autowired
	PatientRepository patRepo;
	
	public Page<PatientEntity> getPagablePatients(int page, int pageSize){
		
		Page<PatientEntity> patRes=patRepo.findAll(PageRequest.of(page, pageSize));
		
//		Page<PatientDTO> res=patRes.map(pat->
//					new PatientDTO().getPatientDTOWithoutAddress(pat)
//				);
				
		
		return patRes;
	}
}
