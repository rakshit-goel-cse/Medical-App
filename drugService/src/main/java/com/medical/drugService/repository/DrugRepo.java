package com.medical.drugService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.drugService.entity.DrugEntity;

public interface DrugRepo extends JpaRepository<DrugEntity, Integer> {

}
