package com.medical.userService.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="user")
public class UserEntity {
	
	public enum UserType{
		ADMIN,
		EMP,
		PAT
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="pat_id")
	private Integer patId;
	
	@Column(name="emp_id")
	private Integer empId;
	
	 @Enumerated(EnumType.STRING) // Use STRING to store enum name in the database
	 @Column(nullable = false)
	 private UserType type;
	 
	 @Column(nullable = false,unique = true)
	 private String userName;
	 
	 @Column(nullable = false)
	 private String password;
}
