package com.medical.userService.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserLoginDto {
	
	private int id;
	private int patientId;
	private int employeeId;
	
	private String type;
	private String userName;
	private String password;
}
