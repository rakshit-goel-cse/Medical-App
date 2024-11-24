package com.medical.prescriberService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.prescriberService.entity.PrescriberEntity;

public interface PrescriberRepo extends JpaRepository<PrescriberEntity, Integer> {

}
