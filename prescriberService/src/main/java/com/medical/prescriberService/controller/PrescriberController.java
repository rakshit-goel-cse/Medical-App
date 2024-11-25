package com.medical.prescriberService.controller;

import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.slf4j.SLF4JLogBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.prescriberService.dto.PrescriberRespDto;
import com.medical.prescriberService.service.PrescriberGetService;
import com.medical.prescriberService.service.PrescriberSetService;

@RestController
@RequestMapping("/prescriber")
public class PrescriberController {

	
	Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PrescriberGetService getService;
	
	@Autowired
	PrescriberSetService setService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getPresById(@PathVariable(name="id") int id){
		return new ResponseEntity<Object>(getService.getPrescriber(id),HttpStatus.FOUND);
	}
	
	@GetMapping("withAddress/{id}")
	public ResponseEntity<Object> getPresWithAddById(@PathVariable(name="id") int id,
			@RequestParam(name = "allActive", defaultValue = "true") Boolean allActive) {
		PrescriberRespDto presDto = getService.getPrescriber(id);

		if (allActive) {
			presDto.setAddress(getService.getActiveAdd(id));
		} else {
			presDto.setAddress(getService.getAllAdd(id));
		}

		return new ResponseEntity<Object>(presDto, HttpStatus.FOUND);
	}
	
	@PostMapping("addPrescriber")
	public ResponseEntity<Object> addPrescriber(@RequestBody PrescriberRespDto presDto){
		
		log.info("Pres data- "+presDto.toString());
		
		Boolean flag=setService.createNewPrescriber(presDto);
		
		return ResponseEntity.ok(
					flag?"prescriber created":"some error occured while creating prescriber"
				);
		
	}
}
