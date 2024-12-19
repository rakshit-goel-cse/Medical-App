package com.medical.gateway.dto;


public class UserDto {
	
	public UserDto(int id, int patientId, int employeeId, String type, String userName, String password) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.employeeId = employeeId;
		this.type = type;
		this.userName = userName;
		this.password = password;
	}
	
	
	public UserDto() {
		super();
	}


	private int id;
	private int patientId;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getPatientId() {
		return patientId;
	}


	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	private int employeeId;
	
	private String type;
	private String userName;
	private String password;
}
