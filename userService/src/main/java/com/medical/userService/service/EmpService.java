package com.medical.userService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.userService.dto.employee.EmployeeDTO;
import com.medical.userService.repository.EmployeeRepo;

@Service
public class EmpService {

	@Autowired
	EmployeeRepo empRepo;
	
	public EmployeeDTO getEmployeeById(int id) {
		Optional<EmployeeDTO> employeeDTO = empRepo.findById(id).map(empE->
				EmployeeDTO.builder()
					.dob(empE.getDob())
					.gender(empE.getGender())
					.name(empE.getName())
					.build()
					);
		return employeeDTO.get();
	}
}
