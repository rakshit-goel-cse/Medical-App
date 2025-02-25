package com.medical.userService.entity.patient;

import java.sql.Date;
import java.util.List;

import com.medical.userService.dto.patient.PatientAddressDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "patient")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PatientEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private Date dob;
	private int height;
	private int weight;
	private char gender;
	private char is_active;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = false,fetch = FetchType.LAZY)
//	@JsonManagedReference // This indicates that this is the "managed" side of the relationship
	private List<PatientAddressEntity> patAdd;

//	@OneToMany(mappedBy = "patient"): Defines a one-to-many relationship where mappedBy refers to the field in the PatientAddressEntity class that holds the reference to PatientEntity.
//	cascade = CascadeType.ALL: Ensures that all operations (persist, merge, remove, etc.) are cascaded to PatientAddressEntity objects.
//	orphanRemoval = true: Ensures that if an address is removed from the patientAdd list, it is also deleted from the database.

}
