package com.medical.userService.dto.user;

import com.medical.userService.dto.employee.EmployeeDTO;
import com.medical.userService.dto.patient.PatientDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserDto {
	
	public enum UserType{
		ADMIN,
		EMP,
		PAT
	}
	
	private int id;
	private PatientDTO patient;
	private EmployeeDTO employee;
	
	private String type;
	private String userName;
	private String password;
}
