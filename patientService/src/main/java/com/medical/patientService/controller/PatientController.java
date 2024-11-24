package com.medical.patientService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.patientService.dto.PatientDTO;
import com.medical.patientService.entity.PatientEntity;
import com.medical.patientService.exception.InvalidIdException;
import com.medical.patientService.repository.PatientRepository;

@RestController

public class PatientController {

	@Autowired
	PatientRepository patRepo;
	
	@GetMapping("/getPatientWithoutAddress")
	public ResponseEntity<Object> getPatientWithoutAdd(@RequestParam(name="id") int id){
		if(id<1) {
			throw new InvalidIdException("Patient Id is Invalid Kindly recheck");
		}
		
		return patRepo.findById(id)
				.map(data->{
					return new ResponseEntity<Object>(new PatientDTO().getPatientDTOWithoutAddress(data),HttpStatus.OK);
				})
				.orElse(new ResponseEntity<>("No data found",HttpStatus.NOT_FOUND));
	}
	

	@GetMapping("/getPatientWithAddress")
	public ResponseEntity<Object> getPatientWithAdd(@RequestParam(name="id") int id,
													@RequestParam(name="isActive",required = false,defaultValue = "true") Boolean isActive
													){
		if(id<1) {
			throw new InvalidIdException("Patient Id is Invalid Kindly recheck");
		}
		
		return patRepo.findById(id)
				.map(data->{
					return (isActive
					? new ResponseEntity<Object>(new PatientDTO().getPatientDTOWithActiveAddress(data),HttpStatus.OK)
					: new ResponseEntity<Object>(new PatientDTO().getPatientDTOWithAddress(data),HttpStatus.OK));
				})
				.orElseThrow();
	}
	
	@PutMapping("/setPatientWithoutAddress")
	public ResponseEntity<Object> setPatientWithoutAdd(@RequestBody PatientDTO patDto){
		
		PatientEntity patEntity = PatientEntity.builder()
									.name(patDto.getName())
									.dob(patDto.getDob())
									.gender(patDto.getGender())
									.height(patDto.getHeight())
									.weight(patDto.getWeight())
									.is_active('Y')
									.build();
		 patRepo.save(patEntity);
		return new ResponseEntity<Object>("patient saved",HttpStatus.CREATED);
	}
	
}
