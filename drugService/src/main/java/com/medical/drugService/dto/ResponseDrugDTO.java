package com.medical.drugService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDrugDTO {

	private int id;
	private String name;
	private String usage;
	private int maxQty;
}
