package com.medical.prescriptionService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.prescriptionService.dto.PrescriptionRequestDto;
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
	
	@GetMapping("/getActiveByPatId")
	public ResponseEntity<Object> getActivePrescByPatId(@RequestParam(name = "patId") int id,
			@RequestParam(name = "offSet", defaultValue = "0") int offSet,
			@RequestParam(name = "pagesize", defaultValue = "10") int pageSize) {
		return ResponseEntity.ok(getService.getPrescPagerByPatId(id, offSet, pageSize));
	}
	
	@PostMapping("/addRandom/{entries}")
	public void addRandom(@PathVariable(name="entries") int entries) {
		System.out.println("Random entries called for entries- "+entries);
		setService.addRandom(entries);
	}
	
}
