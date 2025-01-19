package com.medical.userService.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.userService.dto.patient.PatientDTO;
import com.medical.userService.entity.patient.PatientEntity;
import com.medical.userService.exception.InvalidIdException;
import com.medical.userService.repository.PatientRepository;
import com.medical.userService.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	PatientRepository patRepo;
	
	@Autowired
	PatientService patService;
	
	@GetMapping("/getPatientWithoutAddress")
	public ResponseEntity<PatientDTO> getPatientWithoutAdd(@RequestParam(name="id") int id){
		if(id<1) {
			throw new InvalidIdException("Patient Id is Invalid Kindly recheck");
		}
		
		return patRepo.findById(id)
				.map(data->{
					return new ResponseEntity<PatientDTO>(
							new PatientDTO()
								.getPatientDTOWithoutAddress(data)
								,HttpStatus.OK);
				})
				.orElse(new ResponseEntity<>(new PatientDTO(),HttpStatus.NOT_FOUND));
	}
	

	@GetMapping("/getPatientWithAddress")
	public ResponseEntity<PatientDTO> getPatientWithAdd(@RequestParam(name="id") int id,
													@RequestParam(name="isActive",required = false,defaultValue = "true") Boolean isActive
													){
		if(id<1) {
			throw new InvalidIdException("Patient Id is Invalid Kindly recheck");
		}
		
		return patRepo.findById(id)
				.map(data->{
					return (isActive
					? new ResponseEntity<PatientDTO>(new PatientDTO().getPatientDTOWithActiveAddress(data),HttpStatus.OK)
					: new ResponseEntity<PatientDTO>(new PatientDTO().getPatientDTOWithAddress(data),HttpStatus.OK));
				})
				.orElseThrow();
	}
	
	public Integer savePatientWithoutAddress(PatientDTO patDto) {
		PatientEntity patEntity = PatientEntity.builder().name(patDto.getName())
				.gender(patDto.getGender()).height(patDto.getHeight()).weight(patDto.getWeight()).is_active('Y')
				.dob(Date.valueOf(patDto.getDob()))
				.build();
		if(null== patRepo) throw new InvalidIdException("Repo is null");
		patEntity = patRepo.save(patEntity);
		if (patEntity.getId() < 0) {
			throw new InvalidIdException("Patient not saved");
		}
		return patEntity.getId();
	}
	
	@PutMapping("/setPatientWithoutAddress")
	public ResponseEntity<Object> setPatientWithoutAdd(@RequestBody PatientDTO patDto){
		
//		PatientEntity patEntity = PatientEntity.builder()
//									.name(patDto.getName())
//									.dob(patDto.getDob())
//									.gender(patDto.getGender())
//									.height(patDto.getHeight())
//									.weight(patDto.getWeight())
//									.is_active('Y')
//									.build();
//		 patEntity= patRepo.save(patEntity);
		savePatientWithoutAddress(patDto);
		return new ResponseEntity<Object>("patient saved",HttpStatus.CREATED);
	}
	
	@GetMapping("/getPatientList")
	public ResponseEntity<Page<PatientEntity>> getPaginatedPatients(
			@RequestParam(name="offset", defaultValue="0",required = false) int page, 
				@RequestParam(name="pageSize",defaultValue = "5",required = false) int pageSize){
		return ResponseEntity.ok(patService.getPagablePatients(page, pageSize));
	}
	
	
	
}
