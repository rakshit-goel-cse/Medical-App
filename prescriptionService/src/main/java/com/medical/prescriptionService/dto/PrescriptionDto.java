package com.medical.prescriptionService.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionDto {

	private int id;
	
	private int patId;
	
	private PrescriberDto prescriber;
	private DrugDTO drug;
	
//	@JsonFormat(pattern = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)") //change the date format
	private Date creationDate;
//	@JsonFormat(pattern = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)") //change the date format
	private Date pickupDate;
	
	private int storeNum;
	
}
