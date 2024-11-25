package com.medical.prescriptionService.dto;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriberDto {

private int id;
	
	private String name;
	@JsonFormat(pattern = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)") //change the date format
	private Date dob;
	private String gender;
	private String type;
	private String isActive;
	
	private List<AddressRespDTO> address;
}
