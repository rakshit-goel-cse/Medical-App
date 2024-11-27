package com.medical.userService.dto.patient;

import com.medical.userService.entity.patient.PatientAddressEntity;

import lombok.Data;

@Data
public class PatientAddressDTO {

	public PatientAddressDTO(PatientAddressEntity patAddEnt) {
		this.addLine1=patAddEnt.getAddLine1();
		this.addLine2=patAddEnt.getAddLine2();
		this.city=patAddEnt.getCity();
		this.state=patAddEnt.getState();
		this.zipcode=patAddEnt.getZipcode();
		this.isActive=patAddEnt.getIsActive();
	}
	
	private String addLine1;
	
	private String addLine2;
	
	private String city;
	
	private String state;
	
	private int zipcode;
	
	private char isActive;
}
