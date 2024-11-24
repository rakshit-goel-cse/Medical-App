package com.medical.drugService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDrugDTO {

	private int id;
	private String name;
	private String usage;
	private int maxQty;
}
