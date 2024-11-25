package com.medical.prescriptionService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRespDTO {

private int id;
	
	private int addId;
	private String addLine1;
	
	private String addLine2;
	
	
	private String city;
	
	
	private String state;
	
	
	private String zipCode;
	
	
	private String isActive;
	
	
	private int prescriberId;
}
