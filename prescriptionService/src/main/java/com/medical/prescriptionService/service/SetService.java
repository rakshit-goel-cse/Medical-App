package com.medical.prescriptionService.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.prescriptionService.dto.PrescriptionRequestDto;
import com.medical.prescriptionService.entity.PrescriptionEntity;
import com.medical.prescriptionService.repo.PrescriptionRepo;

@Service
public class SetService {

	@Autowired
	PrescriptionRepo repo;
	
	public void newPrescription(PrescriptionRequestDto dto) {
		PrescriptionEntity ent=PrescriptionEntity.builder()
									.creationDate(dto.getCreationDate())
									.drugId(dto.getDrugId())
									.patId(dto.getPatId())
									.pickupDate(dto.getPickupDate())
									.prescriberId(dto.getPrescriberId())
									.storeNumber(dto.getStoreNum())
									.build();
		
		repo.save(ent);
	}
	
	
	public void addRandom(int amount) {
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(new java.util.Date());
		cal.add(Calendar.MONTH, -1);
		
		Calendar cal2=Calendar.getInstance();
		cal2.setTime(new java.util.Date());
		cal2.add(Calendar.WEEK_OF_MONTH, 1);
		
		Random random=new Random();
		
		while(amount>0) {
			
			
			
			PrescriptionEntity ent=PrescriptionEntity.builder()
					.creationDate(getRandomDate(cal.getTimeInMillis(),new java.util.Date().getTime()))
					.drugId(random.nextInt(6) +1)
					.patId(1)
					.pickupDate(getRandomDate(new java.util.Date().getTime(),cal2.getTimeInMillis()))
					.prescriberId(random.nextInt(19-15)+15)
					.storeNumber(21)
					.build();

			repo.save(ent);
			
			
			amount--;
		}
	}
	
	private static Date getRandomDate(long startMillis, long endMillis) {
        // Get the milliseconds of the start and end dates
//        long startMillis = startDate.getTime();
//        long endMillis = endDate.getTime();

        // Generate a random number of milliseconds
        long randomMillis = ThreadLocalRandom.current().nextLong(startMillis, endMillis);

        // Return the new Date object
        return new Date(randomMillis);
    }
}
