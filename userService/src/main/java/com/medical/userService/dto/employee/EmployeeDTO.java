package com.medical.userService.dto.employee;

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
public class EmployeeDTO {
	
	private String name;
	@JsonFormat(pattern = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)") //change the date format
	private Date dob;
	private char gender;
	private char isActive;
	
	
}
