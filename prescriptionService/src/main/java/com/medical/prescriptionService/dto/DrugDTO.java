package com.medical.prescriptionService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugDTO {

	private int id;
	private String name;
	private String usage;
	private int maxQty;
}
