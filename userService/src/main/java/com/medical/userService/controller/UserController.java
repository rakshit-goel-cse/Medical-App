package com.medical.userService.controller;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;
import com.medical.userService.dto.employee.EmployeeDTO;
import com.medical.userService.dto.patient.PatientDTO;
import com.medical.userService.dto.user.UserDto;
import com.medical.userService.dto.user.UserLoginDto;
import com.medical.userService.entity.user.UserEntity;
import com.medical.userService.exception.InvalidIdException;
import com.medical.userService.repository.UserRepo;
import com.medical.userService.service.EmpService;
import com.medical.userService.utils.JWTUtils;

import ch.qos.logback.core.util.StringUtil;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepo userRepo;
	@Autowired
	EmployeeController empC;
	@Autowired
	PatientController patC;
	
	@Autowired
	EmpService empService;
	
	@Autowired
	JWTUtils jwtUtil;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	@PostMapping("/addPatient")
	@Transactional
	public ResponseEntity<Object> createNewPatient(@RequestBody UserDto user) {

		log.info("user details to save- " + user.toString());

		if (null == user || null == user.getPatient() || user.getPatient().getName().isEmpty()
				|| user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
			throw new InvalidIdException("Patient Data is Invalid Kindly recheck" + user.toString() + "\n*********\n"
					+ user.getPatient().toString());
		}
		int id = patC.savePatientWithoutAddress(user.getPatient());

		UserEntity ent = UserEntity.builder().patId(id).userName(user.getUserName()).password(user.getPassword())
				.type(UserEntity.UserType.PAT).build();

		userRepo.save(ent);

		return new ResponseEntity<Object>("Success", HttpStatus.CREATED);
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<Object> createNewEmployee(UserDto user) {
		if (null == user || null == user.getEmployee() || user.getUserName().isEmpty()
				|| user.getPassword().isEmpty()) {
			throw new InvalidIdException("Employee Data is Invalid Kindly recheck");
		}

		int id = empC.saveEmployeeWithoutAddress(user.getEmployee());

		UserEntity ent = UserEntity.builder().patId(id).userName(user.getUserName()).password(user.getPassword())
				.type(UserEntity.UserType.EMP).build();

		userRepo.save(ent);

		return new ResponseEntity<Object>("Success", HttpStatus.CREATED);
	}

	@GetMapping("/getPassword/{username}")
	public UserLoginDto getPassword(@PathVariable(name = "username", required = true) String userName) {
		if (null == userName || userName.isEmpty())
			throw new InvalidIdException("User data invalid");

		Optional<UserEntity> res = userRepo.findByUserName(userName);
		UserEntity ent = res.isPresent() ? res.get() : null;
		if (null == ent || ent.getId() < 0 || ent.getPassword().isEmpty() || null == ent.getType()
				|| (null == ent.getEmpId() && null == ent.getPatId()))
			return UserLoginDto.builder().build();

		return UserLoginDto.builder().id(ent.getId()).employeeId(null != ent.getEmpId() ? ent.getEmpId() : 0)
				.password(ent.getPassword()).patientId(null != ent.getPatId() ? ent.getPatId() : 0)
				.type(ent.getType().toString()).userName(ent.getUserName()).build();
	}
	
	@GetMapping("/login")
	public ResponseEntity<Object> login(@RequestParam(name="user-name",required=true) String userName,
				@RequestParam(name="password",required=true) String pass) {
		Map<String, Object> response = new HashMap<>();
		if(StringUtil.notNullNorEmpty(userName) && StringUtil.notNullNorEmpty(pass)) {
			UserLoginDto userDto=getPassword(userName);
			if(null!=userDto && StringUtil.notNullNorEmpty(userDto.getPassword()) && pass.equals(userDto.getPassword())) {
				response.put("token", jwtUtil.generateJWTTocken(userDto.getUserName()));
				
				//creating new user
				UserDto newUser=new UserDto();
				newUser.setType(userDto.getType());
				newUser.setUserName(userDto.getUserName());
				if(null!=userDto.getType() && UserDto.UserType.PAT.toString().equalsIgnoreCase(userDto.getType())) {
					PatientDTO dto= patC.getPatientWithAdd(userDto.getPatientId(),true).getBody();
					newUser.setPatient(dto);
				}
				else if(null!=userDto.getType() && UserDto.UserType.EMP.toString().equalsIgnoreCase(userDto.getType())) {
					EmployeeDTO dto= empService.getEmployeeById(userDto.getEmployeeId());
					newUser.setEmployee(dto);
				}
				
				
				response.put("user", newUser);
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
		}
		//response.put("token", false);
		return new ResponseEntity<>("false",HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/validateJWT")
	public String validateJWTToken(@RequestParam(name = "jwt-token", required = true) String jwt) {
		System.out.println("JWT tocken- "+jwt);
		if (null == jwt || jwt.isEmpty())
			throw new InvalidIdException("User data invalid");
		//use util to validate and create user
		return (
				jwtUtil.validateJwtToken(jwt)
					? jwtUtil.getUserNameFromJwtToken(jwt)
						: ""
		);
	}
}
