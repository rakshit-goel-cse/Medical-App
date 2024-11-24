package com.medical.prescriberService.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="prescriber")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriberEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private Date dob;
	private String gender;
	private String type;
	private String isActive;
}
