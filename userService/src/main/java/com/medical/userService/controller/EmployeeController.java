package com.medical.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.userService.dto.employee.EmployeeDTO;
import com.medical.userService.entity.employee.EmployeeEntity;
import com.medical.userService.entity.patient.PatientEntity;
import com.medical.userService.exception.InvalidIdException;
import com.medical.userService.repository.EmployeeRepo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepo empRepo;
	
	public int saveEmployeeWithoutAddress(EmployeeDTO employee) {
		EmployeeEntity empEntity = EmployeeEntity.builder().name(employee.getName()).dob(employee.getDob())
				.gender(employee.getGender()).is_active('Y')
				.build();
		empEntity = empRepo.save(empEntity);
		if (empEntity.getId() < 0) {
			throw new InvalidIdException("Employee not saved");
		}
		return empEntity.getId();
	}

}
