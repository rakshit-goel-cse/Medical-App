package com.medical.userService.dto.employee;

import java.sql.Date;

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
	private Date dob;
	private char gender;
	private char isActive;
	
	
}
