package com.medical.drugService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.drugService.dto.RequestDrugDTO;
import com.medical.drugService.dto.ResponseDrugDTO;
import com.medical.drugService.entity.DrugEntity;
import com.medical.drugService.exception.InvalidIdException;
import com.medical.drugService.repository.DrugRepo;

@RestController
@RequestMapping("/drug")
public class DrugController {

	@Autowired
	DrugRepo repo;
	
	@GetMapping("getDrugById/{id}")
	public ResponseEntity<Object> getDrugById(@PathVariable(name="id") int id){
		
		if(id<1) throw new InvalidIdException("Drug Id Not Valid");
		
		DrugEntity ent=repo.findById(id).get();
		
		ResponseDrugDTO dto=ResponseDrugDTO.builder()
						.id(ent.getId())
						.name(ent.getName())
						.maxQty(ent.getMaxQuantity())
						.usage(ent.getDrugUsage())
						.build();
		
		return new ResponseEntity<Object>(dto,HttpStatus.FOUND);
	}
	
	@PutMapping("addDrug")
	public ResponseEntity<Object> addDrug(@RequestBody RequestDrugDTO dto){
		DrugEntity ent=DrugEntity.builder()
							.name(dto.getName())
							.drugUsage(dto.getUsage())
							.maxQuantity(dto.getMaxQty())
							.build();
		
		ent=repo.save(ent);
			
		return new ResponseEntity<Object>(ent,HttpStatus.CREATED);
	}
}
