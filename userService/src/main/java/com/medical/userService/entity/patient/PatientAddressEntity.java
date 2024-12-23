package com.medical.userService.entity.patient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="patient_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientAddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Nonnull
	@Column(name = "addLine1")
	private String addLine1;
	
	@Column(name = "addLine2")
	private String addLine2;
	
	@Nonnull
	private String city;
	
	@Nonnull
	private String state;
	
	@Nonnull
	private int zipcode;
	
	private char isActive;
	
	@ManyToOne
	@JoinColumn(name="patient_id" , referencedColumnName = "id")
	@JsonIgnore // This prevents the patient data from being serialized 
//	@JsonBackReference // This prevents the back reference (circular reference) from being serialized
	private PatientEntity patient;
	
//	@ManyToOne: Indicates that many address entries can point to one patient.
//	@JoinColumn(name = "patient_id", referencedColumnName = "id"): Specifies the foreign key 
//	column (patient_id) in the patient_address table, which references the id column of the patient table.
}
