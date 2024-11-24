package com.medical.prescriberService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.google.common.base.Optional;
import com.medical.prescriberService.entity.AddressEntity;

public interface AddressRepo extends JpaRepository<AddressEntity, Integer> {

	public Optional<List<AddressEntity>> findByPrescriberId(int id);
	
	@Query("SELECT a FROM prescriber_address a WHERE a.prescriberId=:id AND a.isActive='Y' ") //table name is the entity name and make sure to use : for param value
	public Optional<List<AddressEntity>> findActiveByPresId(@Param("id") int id);
}
