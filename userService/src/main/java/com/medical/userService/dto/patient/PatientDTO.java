package com.medical.userService.dto.patient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.medical.userService.entity.patient.PatientEntity;

import lombok.Data;

@Data
public class PatientDTO {
	
		public PatientDTO getPatientDTOWithoutAddress(PatientEntity patEnt) {
			this.name=patEnt.getName();
			this.dob=LocalDate.parse(patEnt.getDob().toString());
			this.gender=patEnt.getGender();
			this.height=patEnt.getHeight();
			this.weight=patEnt.getWeight();
			this.isActive=patEnt.getIs_active();
			this.id=patEnt.getId();
			
			return this;
		}
		
		public PatientDTO getPatientDTOWithAddress(PatientEntity patEnt) {
			PatientDTO patDto=this.getPatientDTOWithoutAddress(patEnt);
			patDto.setPatAdd(
					patEnt.getPatAdd().stream()
						.map(addEnt->
								new PatientAddressDTO(addEnt)
								)
						.toList()
					);
			return patDto;
		}
		
		public PatientDTO getPatientDTOWithActiveAddress(PatientEntity patEnt) {
			PatientDTO patDto=this.getPatientDTOWithoutAddress(patEnt);
			patDto.setPatAdd(
					patEnt.getPatAdd().stream()
						.filter(addEnt->addEnt.getIsActive()=='Y')
						.map(addEnt->
								new PatientAddressDTO(addEnt)
								)
						.toList()
					);
			return patDto;
		}
	
	private int id;
	private String name;
	
	//@JsonFormat(pattern = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)") //change the date format
	private LocalDate dob;
	
	private int height;
	private int weight;
	private char gender;
	private char isActive;
	
	private List<PatientAddressDTO> patAdd;
	
}
