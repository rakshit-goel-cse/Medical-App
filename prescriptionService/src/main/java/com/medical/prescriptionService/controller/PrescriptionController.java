package com.medical.prescriptionService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.prescriptionService.dto.PrescriptionRequestDto;
import com.medical.prescriptionService.dto.PrescriptionResponseDto;
import com.medical.prescriptionService.service.GetService;
import com.medical.prescriptionService.service.SetService;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

	@Autowired
	GetService getService;
	
	@Autowired
	SetService setService;
	
	@GetMapping("/getAllByPatId")
	public ResponseEntity<Object> getAllPrescByPatId(@RequestParam(name="patId") int id){
		return ResponseEntity.ok(getService.getPrescByPatId(id));
	}
	
	@PostMapping("/createNew")
	public ResponseEntity<Object> createNewPresc(@RequestBody PrescriptionRequestDto dto){
		setService.newPrescription(dto);
		return ResponseEntity.ok("Prescription created");
	}
}
