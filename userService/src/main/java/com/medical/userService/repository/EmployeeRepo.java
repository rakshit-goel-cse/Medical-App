package com.medical.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.userService.entity.employee.EmployeeEntity;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {

}
